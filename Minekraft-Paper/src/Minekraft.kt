package org.fsqrt.minekraft.paper


import org.bukkit.plugin.java.JavaPlugin
import org.fsqrt.minekraft.annotation.MinekraftInternal
import org.fsqrt.minekraft.module.VanillaModule
import org.fsqrt.minekraft.paper.module.PaperModule
import org.fsqrt.minekraft.paper.tasks.KRunnableHolder
import org.fsqrt.minekraft.services.ConcurrentHashMapServiceRegistryImpl
import org.fsqrt.minekraft.services.ServiceRegistry

/**
 * The Main instance of the Plugin
 * @since 0.0.1
 * @author Max Bossing
 */
lateinit var MinekraftInstance: Minekraft
    private set

/**
 * The Paper Entrypoint for Minekraft
 *
 * When working with Minekraft, extend from this class and not [JavaPlugin]
 *
 * Instead of overriding [onLoad], [onEnable] and [onDisable], override:
 * - [load] - at server startup - not everything is ready
 * - [start] - called when everything serverside is ready
 * - [stop] - called at server shutdown
 *
 * @author Max Bossing
 * @since 0.0.1
 */
@OptIn(MinekraftInternal::class)
abstract class Minekraft : JavaPlugin() {
    private val kRunnableHolderProperty = lazy { KRunnableHolder }
    internal val kRunnableHolder by kRunnableHolderProperty

    /**
     * [PaperModule]s that will be integrated into the Minekraft lifecycle
     */
    open val modules: List<PaperModule>? = null

    /**
     * A general-purpose [ServiceRegistry] that can be used to store and access Minekraft Components
     *
     * This registry also auto-closes all resources when the Minekraft lifecycle ends.
     */
    open val serviceRegistry: ServiceRegistry = ConcurrentHashMapServiceRegistryImpl()

    override fun onLoad() {
        if (::MinekraftInstance.isInitialized) {
            server.logger.severe("The Minekraft instance has already been initialized.")
        }
        MinekraftInstance = this
        modules?.forEach { it.load(this) }
        load()
    }

    override fun onEnable() {
        modules?.forEach { it.start(this) }
        start()
    }

    override fun onDisable() {
        stop()
        serviceRegistry.close()
        modules?.forEach { it.stop(this) }
        if (kRunnableHolderProperty.isInitialized()) kRunnableHolderProperty.value.close()
    }

    /**
     * Called on plugin load
     */
    open fun load() {}

    /**
     * Called on plugin start
     */
    open fun start() {}

    /**
     * Called on plugin stop
     */
    open fun stop() {}
}