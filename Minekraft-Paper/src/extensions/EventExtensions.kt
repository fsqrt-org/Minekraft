package org.fsqrt.minekraft.paper.extensions

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.PrepareItemCraftEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

/**
 * Adds cancellation for parity with other events
 *
 * If the event is cancelled, the result will be set to [Material.AIR].
 * If the event is uncancelled later, it will be set to the original result
 * @author Jakob Kretzschmar (https://github.com/jakobkmar)
 * @since 0.0.1
 */
var PrepareItemCraftEvent.isCancelled: Boolean
    get() = inventory.result?.type == Material.AIR
    set(value) {
        if (value) inventory.result = ItemStack(Material.AIR) else inventory.result = inventory.recipe?.result
    }

/**
 * Returns the item used in the interaction
 * @return The item used in the interaction, null if none used
 * @author Jakob Kretzschmar (https://github.com/jakobkmar)
 * @since 0.0.1
 */
val PlayerInteractEntityEvent.usedItem: ItemStack?
    get() {
        val p: Player = this.player
        return when (this.hand) {
            EquipmentSlot.HAND -> p.inventory.itemInMainHand
            EquipmentSlot.OFF_HAND -> p.inventory.itemInOffHand
            else -> null
        }
    }