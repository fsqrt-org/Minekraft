package org.fsqrt.minekraft.paper

import net.kyori.adventure.text.logger.slf4j.ComponentLogger
import org.bukkit.Bukkit
import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.ServicesManager
import java.util.logging.Logger

/**
 * All online players
 * @since 0.0.1
 * @author Max Bossing
 */
val players: Collection<Player> get() = Bukkit.getOnlinePlayers()


/**
 * The server object
 * @since 0.0.1
 * @author Max Bossing
 */
val server: Server get() = Bukkit.getServer()

/**
 * The Bukkit Plugin Manager
 * @since 0.0.1
 * @author Max Bossing
 */
val pluginManager: PluginManager get() = Bukkit.getPluginManager()

/**
 * The Bukkit Services Manager
 * @since 0.0.1
 * @author Max Bossing
 */
val servicesManager: ServicesManager get() = Bukkit.getServicesManager()

/**
 * The [Logger] instance for the Plugin
 * @since 0.0.1
 * @author Max Bossing
 */
val logger: Logger get() = MinekraftInstance.logger

/**
 * The [ComponentLogger] instance for the Plugin
 * @since 0.0.1
 * @author Max Bossing
 */
val componentLogger: ComponentLogger = MinekraftInstance.componentLogger