package org.fsqrt.minekraft.text

import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor

/**
 * Global Color Settings
 */
data class AudienceData(
    var prefix: String = "fsqrt.org",
    var cDefault: TextColor = NamedTextColor.GRAY,
    var cSecondary: TextColor = NamedTextColor.DARK_GRAY,
    var cHighlight: TextColor = NamedTextColor.GOLD,
    var cHighlightValues: TextColor = NamedTextColor.WHITE,
    var cDestructive: TextColor = NamedTextColor.RED,
    var cSuccess: TextColor = NamedTextColor.GREEN,
)