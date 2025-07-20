package org.fsqrt.minekraft.paper.tasks

import org.bukkit.scheduler.BukkitRunnable
import org.fsqrt.minekraft.annotation.MinekraftInternal
import org.fsqrt.minekraft.utils.SilentCloseable

/**
 * Responsible for executing the End Callbacks for [KTask]s
 * @author Jakob Kretzschmar (https://github.com/jakobkmar)
 * @since 0.0.1
 */
@MinekraftInternal
internal object KRunnableHolder : SilentCloseable {
    private val runnableEndCallbacks = HashMap<BukkitRunnable, Pair<() -> Unit, Boolean>>()
    override fun close() {
        runnableEndCallbacks.values.forEach { if (it.second) it.first.invoke() }
        runnableEndCallbacks.clear()
    }

    fun add(runnable: BukkitRunnable, callback: () -> Unit, safe: Boolean) =
        runnableEndCallbacks.put(runnable, Pair(callback, safe))

    fun remove(runnable: BukkitRunnable) = runnableEndCallbacks.remove(runnable)
    fun activate(runnable: BukkitRunnable) = runnableEndCallbacks.remove(runnable)?.first?.invoke()
}