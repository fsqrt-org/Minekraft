package org.fsqrt.minekraft.paper.utils

import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataHolder
import org.bukkit.persistence.PersistentDataType
import org.fsqrt.minekraft.paper.MinekraftInstance
import org.fsqrt.minekraft.paper.items.meta

private fun key(key: String) = NamespacedKey(MinekraftInstance, "marker_$key")

/**
 * Mark a [PersistentDataHolder] with the given [key]
 * @param key The key to mark with
 * @since 0.0.1
 * @author Jakob Kretzschmar (https://github.com/jakobkmar)
 */
fun PersistentDataHolder.mark(key: String) =
    persistentDataContainer.set(key(key), PersistentDataType.BYTE, 1.toByte())

/**
 * Remove a Marker from a [PersistentDataHolder]
 * @param key The key to
 * @since 0.0.1
 * @author Jakob Kretzschmar (https://github.com/jakobkmar)
 */
fun PersistentDataHolder.unmark(key: String) = persistentDataContainer.remove(key(key))
/**
 * Check if a [PersistentDataHolder] has been marked with [key]
 * @param key The key to check for
 * @since 0.0.1
 * @author Jakob Kretzschmar (https://github.com/jakobkmar)
 */
fun PersistentDataHolder.isMarked(key: String) = persistentDataContainer.has(key(key), PersistentDataType.BYTE)

/**
 * @see [PersistentDataHolder.mark]
 */
fun ItemStack.mark(key: String) = meta<ItemMeta> { mark(key) }

/**
 * @see [PersistentDataHolder.unmark]
 */
fun ItemStack.unmark(key: String) = meta<ItemMeta> { unmark(key) }

/**
 * @see [PersistentDataHolder.isMarked]
 */
fun ItemStack.isMarked(key: String): Boolean = itemMeta.isMarked(key)
