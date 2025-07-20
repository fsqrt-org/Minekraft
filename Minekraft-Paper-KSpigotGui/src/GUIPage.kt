package org.fsqrt.minekraft.paper.gui.kspigot

class GUIPage<T : ForInventory>(
    val number: Int,
    internal val slots: Map<Int, GUISlot<T>>,
    val transitionTo: PageChangeEffect?,
    val transitionFrom: PageChangeEffect?,
)
