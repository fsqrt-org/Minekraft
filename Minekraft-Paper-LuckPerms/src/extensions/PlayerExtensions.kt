package org.fsqrt.minekraft.paper.luckperms.extensions

import com.nimbusds.oauth2.sdk.id.Audience
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.luckperms.api.model.group.Group
import net.luckperms.api.node.NodeType
import org.bukkit.entity.Player
import org.fsqrt.minekraft.paper.luckperms.luckPerms
import java.util.UUID

val UUID.groups: List<Group>?
    get() = luckPerms.userManager.getUser(this)?.nodes?.filter { NodeType.INHERITANCE.matches(it) }
        ?.mapNotNull { NodeType.INHERITANCE.cast(it).groupName }
        ?.mapNotNull { luckPerms.groupManager.getGroup(it) }

val UUID.groupsWithExpiry: List<Pair<Group, Long?>>?
    get() = luckPerms.userManager.getUser(this)?.nodes?.filter { NodeType.INHERITANCE.matches(it) }
        ?.mapNotNull { NodeType.INHERITANCE.cast(it) }?.map { luckPerms.groupManager.getGroup(it.groupName)!! to it.expiry?.toEpochMilli() }

val UUID.primaryGroup: Group?
    get() = groups?.maxByOrNull { it.absoluteWeight }

fun UUID.prefix(name: String): Component? {
    val group = primaryGroup ?: return null
    val rawPrefix = group.prefix ?: return null
    return MiniMessage.miniMessage().deserialize("<white>$rawPrefix", Placeholder.unparsed("name", name), Placeholder.parsed("color",
        "<${group.color?.asHexString() ?: "#FFFFFF"}>"
    ))
}

val Player.badge: Component?
    get() {
        val badge = luckPerms.getPlayerAdapter(Player::class.java).getMetaData(this).getMetaValue("badge.display") ?: return null
        return MiniMessage.miniMessage().deserializeOrNull(badge)
    }