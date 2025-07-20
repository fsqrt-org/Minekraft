package org.fsqrt.minekraft.paper.gui.kspigot.elements

import org.bukkit.inventory.ItemStack
import org.fsqrt.minekraft.paper.gui.kspigot.ForInventory
import org.fsqrt.minekraft.paper.gui.kspigot.GUIClickEvent
import org.fsqrt.minekraft.paper.gui.kspigot.GUIPageChangeCalculator
import org.fsqrt.minekraft.paper.gui.kspigot.PageChangeEffect
import org.fsqrt.minekraft.paper.gui.kspigot.changePage

class GUIButtonPageChange<T : ForInventory>(
    icon: ItemStack,
    calculator: GUIPageChangeCalculator,
    shouldChange: ((GUIClickEvent<T>) -> Boolean)?,
    onChange: ((GUIClickEvent<T>) -> Unit)?,
) : GUIButton<T>(icon, {
    val currentPage = it.guiInstance.currentPage
    val newPage = it.guiInstance.getPage(
        calculator.calculateNewPage(
            it.guiInstance.currentPageInt,
            it.guiInstance.gui.data.pages.keys
        )
    )
    if (newPage != null) {
        val changePage = shouldChange?.invoke(it) ?: true

        if (changePage) {
            val effect = (newPage.transitionTo ?: currentPage.transitionFrom)
                ?: PageChangeEffect.INSTANT

            it.guiInstance.changePage(effect, currentPage, newPage)
            onChange?.invoke(it)
        }
    }
})
