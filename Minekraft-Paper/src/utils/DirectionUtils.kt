package org.fsqrt.minekraft.paper.utils


import org.bukkit.Location

/**
 * Represents the directions in which the camera can be moved vertically
 * @author Jakob Kretzschmar (https://github.com/jakobkmar)
 * @since 0.0.1
 */
enum class VerticalDirection { UP, DOWN, STRAIGHT }

/**
 * Represents the direction in which the camera can be moved horizontally as cardinal directions
 * @since 0.0.1
 * @author Jakob Kretzschmar (https://github.com/jakobkmar)
 */
enum class CardinalDirection { NORTH, EAST, SOUTH, WEST }

/**
 * Calculates the [VerticalDirection] from a [Location]
 * @return the [VerticalDirection] most fitting for this [Location]
 * @author Jakob Kretzschmar (https://github.com/jakobkmar)
 * @since 0.0.1
 */
fun Location.verticalDirection(): VerticalDirection = when {
    pitch <= -45 -> VerticalDirection.DOWN
    pitch >= 45 -> VerticalDirection.UP
    else -> VerticalDirection.STRAIGHT
}

/**
 * Calculates the [CardinalDirection] from the given [Location]
 * @return The [CardinalDirection] most fitting for this [Location]
 * @author Jakob Kretzschmar (https://github.com/jakobkmar)
 * @since 0.0.1
 */
fun Location.cardinalDirection() = (if (yaw < 0) yaw + 360f else yaw).let {
    when {
        it >= 315 || it < 45 -> CardinalDirection.SOUTH
        it < 135 -> CardinalDirection.WEST
        it < 225 -> CardinalDirection.NORTH
        it < 315 -> CardinalDirection.EAST
        else -> CardinalDirection.NORTH
    }
}
