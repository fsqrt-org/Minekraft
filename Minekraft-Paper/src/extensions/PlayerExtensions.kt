package org.fsqrt.minekraft.paper.extensions

import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

/**
 * Set the players saturation to the maximum level possible
 * @since 0.0.1
 * @author Max Bossing
 */
fun Player.saturate() {
    saturation = foodLevel.toFloat()
}

/**
 * Get the item in the Players hand
 * @param hand The [EquipmentSlot] to get
 * @return The item in the [EquipmentSlot], null if not applicable
 * @author Jakob Kretzschmar (https://github.com/jakobkmar)
 * @since 0.0.1
 */
fun Player.hand(hand: EquipmentSlot) = when (hand) {
    EquipmentSlot.HAND -> inventory.itemInMainHand
    EquipmentSlot.OFF_HAND -> inventory.itemInOffHand
    else -> null
}

/**
 * Give [itemStacks] to the Player
 * @param itemStacks The [ItemStack] to add to the players inventory
 * @return A list of all [ItemStack]s that do not fit into the players inventory
 * @author Jakob Kretzschmar (https://github.com/jakobkmar)
 * @since 0.0.1
 */
fun Player.give(vararg itemStacks: ItemStack) = inventory.addItem(*itemStacks)