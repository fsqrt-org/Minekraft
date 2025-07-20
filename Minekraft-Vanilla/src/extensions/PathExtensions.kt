package org.fsqrt.minekraft.extensions

import org.fsqrt.minekraft.json
import java.nio.file.Path
import kotlin.io.path.*

/**
 * Tries to load the contents of the [Path] as [T] using the global [json], returning null on failure or if the [Path] does not exist
 *
 * If the file does not exist, it will be created and [default] (if set) will be dumped  and returned, else null will be returned
 *
 * @param T the [Serializable] that the contents should be deserialized to
 * @return [T] on success, null on failure
 */
inline fun <reified T : Any> Path.loadSerializable(noinline default: (() -> T)? = null): T? {
    return if (exists())
        try {
            json.decodeFromString<T>(readText())
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    else {
        createParentDirectories()
        createFile()
        default?.invoke()?.let {
            this.writeText(json.encodeToString(it))
            return it
        }
        return null
    }
}