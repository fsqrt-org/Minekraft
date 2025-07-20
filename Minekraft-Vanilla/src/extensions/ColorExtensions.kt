package org.fsqrt.minekraft.extensions

import net.kyori.adventure.text.format.TextColor
import java.awt.Color

/**
 * Converts this adventure [TextColor] into a [java.awt.Color] object
 * @return The [java.awt.Color] representation of this [TextColor]
 * @author Max Bossing
 * @since 0.0.1
 */
fun TextColor.awt(): Color = Color(value())

/**
 * Converts this [java.awt.Color] object into an adventure [TextColor]
 * @return The adventure [TextColor] representation of this [java.awt.Color]
 * @author Max Bossing
 * @since 0.0.1
 */
fun java.awt.Color.adventure(): TextColor = TextColor.color(rgb)