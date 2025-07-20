package org.fsqrt.minekraft.paper.extensions

import org.bukkit.entity.Damageable

/**
 * Kill this [Damageable]
 * @since 0.0.1
 * @author Max Bossing
 */
fun Damageable.kill() {
    health = 0.0
}