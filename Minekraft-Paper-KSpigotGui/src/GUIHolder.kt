package org.fsqrt.minekraft.paper.gui.kspigot

import org.bukkit.entity.Player
import org.bukkit.event.inventory.*
import org.bukkit.inventory.Inventory
import org.fsqrt.minekraft.annotation.MinekraftInternal
import org.fsqrt.minekraft.paper.event.listener
import org.fsqrt.minekraft.paper.extensions.closeForViewers

@MinekraftInternal
object GUIHolder : AutoCloseable {
    private val registered = HashMap<Inventory, GUIInstance<ForInventory>>()

    fun register(guiInstance: GUIInstance<ForInventory>) {
        registered[guiInstance.bukkitInventory] = guiInstance
    }

    fun unregister(guiInstance: GUIInstance<ForInventory>) {
        registered -= guiInstance.bukkitInventory
    }

    init {
        listener<InventoryClickEvent> {
            handler { event, listener ->
                val clickedInv = event.clickedInventory ?: return@handler
                val gui = registered[clickedInv] ?: return@handler
                val player = event.playerOrCancel ?: return@handler

                if (gui.isInMove) {
                    event.isCancelled = true
                    return@handler
                }

                if (event.action.isGUIClick)
                    gui.currentPage.slots[event.slot]?.onClick(GUIClickEvent(event, gui, player)) ?: kotlin.run {
                        event.isCancelled = true
                    }
                else
                    event.isCancelled = true
            }
        }

        listener<InventoryDragEvent> {
            handler { event, listener ->
                val inv = event.inventory
                val gui = registered[inv] ?: return@handler
                val player = event.playerOrCancel ?: return@handler

                var ifCancel = false

                for (slotIndex in event.inventorySlots) {
                    val slot = gui.currentPage.slots[slotIndex]
                    if (slot == null) {
                        ifCancel = true
                        break
                    }
                    val clickEvent = InventoryClickEvent(
                        event.view,
                        event.view.getSlotType(slotIndex),
                        slotIndex,
                        ClickType.UNKNOWN,
                        InventoryAction.UNKNOWN
                    )

                    slot.onClick(GUIClickEvent(clickEvent, gui, player))

                    if (clickEvent.isCancelled) {
                        ifCancel = true
                        break
                    }
                }

                if (ifCancel)
                    event.isCancelled = true
            }
        }
    }

    override fun close() {
        registered.keys.forEach { it.closeForViewers() }
        registered.clear()
    }
}

private val InventoryAction.isGUIClick
    get() = this == InventoryAction.PICKUP_ALL || this == InventoryAction.PICKUP_HALF || this == InventoryAction.PICKUP_SOME || this == InventoryAction.PICKUP_ONE || this == InventoryAction.MOVE_TO_OTHER_INVENTORY

private val InventoryInteractEvent.playerOrCancel: Player?
    get() = (whoClicked as? Player) ?: kotlin.run {
        isCancelled = true
        return null
    }