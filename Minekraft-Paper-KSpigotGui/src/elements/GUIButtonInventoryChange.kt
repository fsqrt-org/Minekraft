package org.fsqrt.minekraft.paper.gui.kspigot.elements

import org.bukkit.inventory.ItemStack
import org.fsqrt.minekraft.paper.gui.kspigot.ForInventory
import org.fsqrt.minekraft.paper.gui.kspigot.GUI
import org.fsqrt.minekraft.paper.gui.kspigot.GUIClickEvent
import org.fsqrt.minekraft.paper.gui.kspigot.InventoryChangeEffect
import org.fsqrt.minekraft.paper.gui.kspigot.changeGUI
import org.fsqrt.minekraft.paper.gui.kspigot.openGUIInstance

class GUIButtonInventoryChange<T : ForInventory>(
    icon: ItemStack,
    changeToGUICallback: () -> GUI<*>,
    changeToPageInt: Int?,
    onChange: ((GUIClickEvent<T>) -> Unit)?,
) : GUIButton<T>(icon, {
    val changeToGUI = changeToGUICallback.invoke().getInstance(it.player)
    val effect = (changeToGUI.gui.data.transitionTo ?: it.guiInstance.gui.data.transitionFrom)
        ?: InventoryChangeEffect.INSTANT
    val changeToPage = changeToGUI.getPage(changeToPageInt) ?: changeToGUI.currentPage

    changeToGUI.changeGUI(effect, it.guiInstance.currentPage, changeToPage)

    it.player.openGUIInstance(changeToGUI)

    onChange?.invoke(it)
})
