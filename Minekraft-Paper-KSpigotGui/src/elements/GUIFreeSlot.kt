package org.fsqrt.minekraft.paper.gui.kspigot.elements

import org.fsqrt.minekraft.paper.gui.kspigot.ForInventory
import org.fsqrt.minekraft.paper.gui.kspigot.GUIClickEvent
import org.fsqrt.minekraft.paper.gui.kspigot.GUISlot


class GUIFreeSlot<T : ForInventory> : GUISlot<T>() {
    override fun onClick(clickEvent: GUIClickEvent<T>) {
        /* do nothing */
    }
}
