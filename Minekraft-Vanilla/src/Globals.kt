package org.fsqrt.minekraft

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import net.kyori.adventure.text.minimessage.MiniMessage
import org.fsqrt.minekraft.text.AudienceData

//import org.fsqrt.minekraft.text.AudienceData

/**
 * The global [kotlinx.serialization.json] Instance
 *
 * If any parts of Minekraft use [kotlinx.serialization.json], this is the instance being used
 *
 * To add custom behaviour, overwrite this instance with a custom one
 */
@OptIn(ExperimentalSerializationApi::class)
var json = Json {
    prettyPrint = true
    encodeDefaults = true
    isLenient = true
    classDiscriminator = "type"
    prettyPrintIndent = "  "
    ignoreUnknownKeys = true
}

/**
 * The global [MiniMessage] instance
 *
 * If any parts of Minekraft (ex: [ng.bossi.minekraft.text.miniMessage]) use MiniMessage, this is the instance being used
 *
 * To add custom behavior (eg tag resolvers), overwrite this instance with a custom one
 */
var miniMessage = MiniMessage.miniMessage()

/**
 * The Global [AudienceData]. This contains global colors, prefixes etc, which are used by Components across Minekraft
 * and projects using Minekraft
 *
 * @see AudienceData
 */
val globalAudienceData: AudienceData = AudienceData()
