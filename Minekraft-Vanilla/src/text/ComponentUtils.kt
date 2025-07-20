package org.fsqrt.minekraft.text


import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.space
import net.kyori.adventure.text.TextReplacementConfig
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.format.TextDecoration.State
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import org.fsqrt.minekraft.globalAudienceData
import org.fsqrt.minekraft.miniMessage

/**
 * Create a [Component] with basic styles. By default, every style is deactivated. The component does not inherit the styles of previous Components
 * @param str The content of the [Component]
 * @param color The [TextColor] of the [Component]. If multiple colors are given, the component will be colored with a gradient of those colors (will be deserialized using [miniMessage]!)
 * @param bold If the [Component] should be bold
 * @param italic If the [Component] should be italic
 * @param underlined If the [Component] should be underlined
 * @param strikethrough If the [Component] should be striked through
 * @param obfuscated If the [Component] should be obfuscated (magic chars)
 * @return A [Component] with the given content with the styles applied accordingly
 * @author Max Bossing
 * @since 0.0.1
 */
fun text(
    str: String,
    vararg color: TextColor = arrayOf(Kolors.GRAY),
    bold: Boolean = false,
    italic: Boolean = false,
    underlined: Boolean = false,
    strikethrough: Boolean = false,
    obfuscated: Boolean = false,
): Component =
    (if (color.size == 1) Component.text(str)
        .color(color.first()) else "<gradient:${color.joinToString(":") { it.asHexString() }}>$str</gradient>".miniMessage())
        .apply {
            decorations(
                mapOf(
                    TextDecoration.BOLD to State.byBoolean(bold),
                    TextDecoration.ITALIC to State.byBoolean(italic),
                    TextDecoration.UNDERLINED to State.byBoolean(underlined),
                    TextDecoration.STRIKETHROUGH to State.byBoolean(strikethrough),
                    TextDecoration.OBFUSCATED to State.byBoolean(obfuscated),
                )
            )
        }


/**
 * Shorthand operator to combine [Component]s
 * @param component The Component to append
 * @since 0.0.1
 * @author Max Bossing
 * @return Itself, with [component] appended
 */
operator fun Component.plus(component: Component) = append(component)

/**
 * Replace a [String] in a [Component] with a [Component]
 * @param old The Text which should be replaced
 * @param new The Component to replace [old] with (Will keep its styling)
 * @return Itself, with all instances of [old] replaced by [new]
 * @author Max Bossing
 * @since 0.0.1
 */
fun Component.replace(old: String, new: Component) =
    replaceText(
        TextReplacementConfig
            .builder()
            .match(old)
            .replacement(new)
            .build()
    )

/**
 * Add a [Component] that will be shown when hovering above it with the mouse
 * @param hover The [Component] to show
 * @return Itself, with the hover added
 * @author Max Bossing
 * @since 0.0.1
 */
fun Component.hover(hover: Component) = hoverEvent(asHoverEvent().value(hover))

/**
 * Add a URL that will be opened when clicking the [Component]
 * @param url The URL to open
 * @return Itself, with the URL added as a [ClickEvent]
 * @author Max Bossing
 * @since 0.0.1
 */
fun Component.url(url: String) = clickEvent(ClickEvent.openUrl(url))

/**
 * Add a command that will be executed when clicking the [Component]
 * @param command The command to execute
 * @return Itself, with the command added as a [ClickEvent]
 * @author Max Bossing
 * @since 0.0.1
 */
fun Component.command(command: String) = clickEvent(ClickEvent.runCommand(command))

/**
 * Add a command that will be pasted into the chat window as a suggestion when clicking the [Component]
 * @param suggestion The suggestion to show
 * @return Itself, with the suggestion added as a [ClickEvent]
 * @author Max Bossing
 * @since 0.0.1
 */
fun Component.suggest(suggestion: String) = clickEvent(ClickEvent.suggestCommand(suggestion))

/**
 * Add a [String] that will be copied into the players clipboard when clicking the [Component]
 * @param copy The [String] to copy
 * @return Itself, with the copy added as a [ClickEvent]
 * @author Max Bossing
 * @since 0.0.1
 */
fun Component.copy(copy: String) = clickEvent(ClickEvent.copyToClipboard(copy))

/**
 * Add a callback that will be executed when clicking the [Component]
 * @param callback The Callback that will be executed when clicking the [Component]. it is the [Audience] that clicked the [Component]
 * @return Itself, with the callback added as a [ClickEvent]
 * @author Max Bossing
 * @since 0.0.1
 */
fun Component.callback(callback: (Audience) -> Unit) = clickEvent(ClickEvent.callback(callback))

/**
 * Convert a [String] into a [Component].
 * @return A [Component] containing this as Text
 * @author Max Bossing
 * @since 0.0.1
 */
fun String.component() = Component.text(this)

/**
 * Deserialize a [String] into a [Component] using the global [MiniMessage] instance
 * @return The deserialized [Component]
 * @author Max Bossing
 * @since 0.0.1
 */
fun String.miniMessage(
    vararg tagResolver: TagResolver
) = miniMessage.deserialize(
    this,
    *tagResolver
)

/**
 * Serialize a [Component] into a Legacy Format String (eg ChatColors etc..)
 * @return A [String] containing the legacy representation of this [Component]
 * @author Max Bossing
 * @since 0.0.1
 */
fun Component.legacy() = LegacyComponentSerializer.legacy('§').serialize(this)

/**
 * Extract the Text of the [Component]
 * @return The contents of the [Component] as a [String]
 * @author Max Bossing
 * @since 0.0.1
 */
fun Component.plain() = PlainTextComponentSerializer.plainText().serialize(this)

/**
 * Creates a [Component] in the style of prefixes used for the Server
 * @param name The text which is put in the prefix
 * @return A [Component] in prefix style with [name] as text
 * @author Max Bossing
 * @since 0.0.1
 */
fun prefixComponent(name: String = globalAudienceData.prefix) = text("[", globalAudienceData.cSecondary) + text(name, globalAudienceData.cHighlight) + text("]",
    globalAudienceData.cSecondary) + space()
