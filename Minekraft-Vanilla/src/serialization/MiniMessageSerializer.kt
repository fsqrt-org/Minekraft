package org.fsqrt.minekraft.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.fsqrt.minekraft.miniMessage

/**
 * Alias type for [Component]s serialized with [MiniMessageSerializer]
 * @since 0.0.1
 */
typealias MiniMessageComponent = @Serializable(with = MiniMessageSerializer::class) Component

/**
 * A [KSerializer] for [Component]s using the global [MiniMessage] instance
 * @author Max Bossing
 * @since 0.0.1
 * @see miniMessage
 */
object MiniMessageSerializer : KSerializer<Component> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("MiniMessage", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Component = miniMessage.deserialize(decoder.decodeString())
    override fun serialize(encoder: Encoder, value: Component) = encoder.encodeString(miniMessage.serialize(value))
}