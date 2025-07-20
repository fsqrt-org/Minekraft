package org.fsqrt.minekraft.paper.gui.kspigot.elements

import org.fsqrt.minekraft.paper.gui.kspigot.ForInventory
import org.bukkit.inventory.ItemStack
import org.fsqrt.minekraft.paper.gui.kspigot.GUIClickEvent
import org.fsqrt.minekraft.paper.gui.kspigot.GUIElement

class GUIPlaceholder<T : ForInventory>(
    private val icon: ItemStack,
) : GUIElement<T>() {
    override fun getItemStack(slot: Int) = icon
    override fun onClickElement(clickEvent: GUIClickEvent<T>) {
        clickEvent.bukkitEvent.isCancelled = true
    }
}
