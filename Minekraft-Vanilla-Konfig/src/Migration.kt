package org.fsqrt.minekraft.config.konfig

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * A Migration is a lambda that has a [JsonElement] tree and a version number as argument and returns a [T]?
 *
 * It's used to migrate older versions of config to newer ones, by allowing to parse out the old data into a new
 *
 * @author btwonion (https://github.com/btwonion)
 * @since 0.0.1
 */
typealias Migration<T> = (jsonTree: JsonElement, version: Int?) -> @Serializable T?