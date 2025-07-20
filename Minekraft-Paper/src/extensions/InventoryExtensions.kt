package org.fsqrt.minekraft.paper.extensions

import org.bukkit.inventory.Inventory


/**
 * Closes the [Inventory] for all viewers
 * @since 0.0.1
 * @author Jakob Kretzschmar (https://github.com/jakobkmar)
 */
fun Inventory.closeForViewers() = HashSet(viewers).forEach { it.closeInventory() }