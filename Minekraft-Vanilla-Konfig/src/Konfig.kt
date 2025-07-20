@file:OptIn(MinekraftInternal::class)
package org.fsqrt.minekraft.config.konfig


import kotlinx.serialization.Serializable
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.fsqrt.minekraft.annotation.MinekraftInternal
import org.fsqrt.minekraft.json
import java.nio.file.Path
import kotlin.io.path.*

@MinekraftInternal
var konfigFiles: MutableList<ConfigFile<*>> = mutableListOf()

/**
 * The model of the json being stored.
 *
 * @param version the version of the config. Used for migration
 * @param config the config
 * @author btwonion (https://github.com/btwonion)
 * @since 0.0.1
 */
@MinekraftInternal
@Suppress("SpellCheckingInspection")
@Serializable
data class Konfig<T>(val version: Int, val config: @Serializable T)

/**
 * This is the function that defines the base properties for config serialization.
 *
 * @param path the path, the config file should hold, e.g. '~/.minecraft/config/autodrop/autodrop.json'
 * @param currentVersion the up-to-date config version
 * @param defaultConfig the default config instance for initial config creation
 * @param migration the consumer used to migrate configs to newer versions
 * @since 0.0.1
 * @author btwonion (https://github.com/btwonion)
 */
inline fun <reified T : @Serializable Any> konfig(
    path: Path,
    currentVersion: Int,
    defaultConfig: T,
    noinline migration: Migration<T>
) {
    konfigFiles.add(ConfigFile(T::class, ConfigSettings(path, currentVersion, migration), defaultConfig))
}

/**
 * Loads a Config from Disk and applies a migration if necessary
 *
 * the encoded config or null if the configSettings, which are defined with [konfig], are null
 * @throws IllegalArgumentException if no file config for the class has been
 * @author btwonion (https://github.com/btwonion)
 * @since 0.0.1
 */
@Suppress("unused", "unchecked_cast")
inline fun <reified T : @Serializable Any> loadConfig(): @Serializable T {
    val configFile = konfigFiles.find { it.type == T::class } as? ConfigFile<T>
    if (configFile == null) throw IllegalArgumentException("No config for class ${T::class.simpleName} found!")

    val defaultInstance = configFile.defaultInstance
    val path = configFile.settings.path

    if (path.configInstantiated(defaultInstance)) return defaultInstance

    val text = path.readText()
    return try {
        json.decodeFromString<Konfig<T>>(text).config
    } catch (e: Throwable) {
        handleException(text, configFile)
    }
}

/**
 * Saves a [ConfigFile] to disk
 * If the configSettings are not applied via the [konfig] function the config does not save.
 *
 * @param config the config
 * @throws IllegalArgumentException if no file config for the class has been set
 * @author btwonion (https://github.com/btwonion)
 * @since 0.0.1
 */
@Suppress("unchecked_cast")
inline fun <reified T : @Serializable Any> saveConfig(config: @Serializable T) {
    val file = konfigFiles.find { it.type == T::class } as? ConfigFile<T>
    if (file == null) throw IllegalArgumentException("No config for class ${T::class.simpleName} found!")
    val path = file.settings.path

    path.writeText(json.encodeToString(Konfig(file.settings.currentVersion, config)))
}

/**
 * Either instantiate the default config or return false if the config already existed.
 *
 * @param defaultInstance the default instance of the config
 * @return true if the config was instantiated or false if the config already existed
 */
@MinekraftInternal
inline fun <reified T : @Serializable Any> Path.configInstantiated(defaultInstance: T): Boolean {
    if (exists()) return false
    parent.createDirectories()
    createFile()

    saveConfig<T>(defaultInstance)
    return true
}

/**
 * Handles migration or reset after faulty deserialization of the config.
 *
 * @param fileText the raw config text
 * @param configFile the [ConfigFile] holding all the required data for migration
 * @return the config after migration of reset of the config
 * @author btwonion (https://github.com/btwonion)
 * @since 0.0.1
 */
@MinekraftInternal
inline fun <reified T : @Serializable Any> handleException(
    fileText: String, configFile: ConfigFile<T>
): @Serializable T {
    val jsonTree = runCatching { json.parseToJsonElement(fileText) }.getOrNull() ?: return resetConfig(configFile)
    val version = jsonTree.jsonObject["version"]?.jsonPrimitive?.intOrNull
    if (version == configFile.settings.currentVersion) return resetConfig(configFile)

    val config = configFile.settings.migration.invoke(
        if (version == null) {
            jsonTree
        } else {
            jsonTree.jsonObject["config"] ?: jsonTree
        }, version
    ) as? T
    saveConfig(config ?: configFile.defaultInstance)
    return config ?: configFile.defaultInstance
}

/**
 * Recreates and saves default config.
 *
 * @param configFile the [ConfigFile] used for getting the default config
 * @return the default config
 * @author btwonion (https://github.com/btwonion)
 * @since 0.0.1
 */
@MinekraftInternal
inline fun <reified T : @Serializable Any> resetConfig(configFile: ConfigFile<T>): T {
    saveConfig(configFile.defaultInstance)
    return configFile.defaultInstance
}