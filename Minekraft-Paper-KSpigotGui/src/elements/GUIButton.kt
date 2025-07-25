package org.fsqrt.minekraft.paper.gui.kspigot.elements

import org.bukkit.inventory.ItemStack
import org.fsqrt.minekraft.paper.gui.kspigot.ForInventory
import org.fsqrt.minekraft.paper.gui.kspigot.GUIClickEvent
import org.fsqrt.minekraft.paper.gui.kspigot.GUIElement

open class GUIButton<T : ForInventory>(
    private val icon: ItemStack,
    private val action: (GUIClickEvent<T>) -> Unit,
) : GUIElement<T>() {
    final override fun getItemStack(slot: Int) = icon
    override fun onClickElement(clickEvent: GUIClickEvent<T>) {
        clickEvent.bukkitEvent.isCancelled = true
        action(clickEvent)
    }
}
