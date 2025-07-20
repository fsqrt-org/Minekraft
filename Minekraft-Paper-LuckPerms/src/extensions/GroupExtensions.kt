package org.fsqrt.minekraft.paper.luckperms.extensions

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.luckperms.api.model.group.Group
import net.luckperms.api.node.NodeType
import kotlin.collections.filter

val Group.absoluteWeight: Int
    get() = weight.orElse(0)

val Group.color: TextColor?
    get() {
        val rawColor = nodes.filter { NodeType.META.matches(it) }.map { NodeType.META.cast(it) }.firstOrNull { it.metaKey == "color" }?.metaValue ?: return null
        return TextColor.fromHexString("#$rawColor")
    }

val Group.prefix: String?
    get() = nodes.filter { NodeType.PREFIX.matches(it) }.map { NodeType.PREFIX.cast(it) }.maxByOrNull { it.priority }?.metaValue

val Group.lowPriorityChat: Boolean
    get() = nodes.filter { NodeType.META.matches(it) }.map { NodeType.META.cast(it) }.firstOrNull { it.metaKey == "low-priority-chat" }?.metaValue?.equals("true") ?: false

val Group.displayComponent: Component?
    get() = MiniMessage.miniMessage().deserialize(nodes.filter { NodeType.META.matches(it) }.map { NodeType.META.cast(it) }.firstOrNull { it.metaKey == "display" }?.metaValue ?: "", Placeholder.parsed("color",
        "<${color?.asHexString() ?: "#FFFFFF"}>"
    ))