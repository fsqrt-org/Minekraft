package org.fsqrt.minekraft.config.konfig

import kotlinx.serialization.Serializable
import org.fsqrt.minekraft.annotation.MinekraftInternal
import kotlin.reflect.KClass

/**
 * Represents a Config file (for example /config/test.json)
 * @param type The Class used as the model of this file
 * @param settings The [ConfigSettings] for this file
 * @param defaultInstance The Default Instance of this file
 * @author btwonion (https://github.com/btwonion)
 * @since 0.0.1
 */
@MinekraftInternal
data class ConfigFile<T : @Serializable Any>(
    val type: KClass<T>, val settings: ConfigSettings, val defaultInstance: T
)