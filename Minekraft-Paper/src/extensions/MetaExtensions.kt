package org.fsqrt.minekraft.paper.extensions

import com.destroystokyo.paper.profile.ProfileProperty
import org.bukkit.Bukkit
import org.bukkit.inventory.meta.BookMeta
import org.bukkit.inventory.meta.SkullMeta
import org.fsqrt.minekraft.text.legacy
import java.util.*

/**
 * Get the content of all pages of a [BookMeta]
 * @return The book contents as a String, separated by newlines per page
 * @since 0.0.1
 * @author Jakob Kretzschmar (https://github.com/jakobkmar)
 */
fun BookMeta.content() = StringBuilder().apply {
    for (it in pages().map { it.legacy() }) {
        if (isNotEmpty())
            append('\n')
        append(it)
    }
}.toString()

/**
 * Applies the texture in [base64] to the [SkullMeta]
 * @param base64 An MC skin encoded in base64
 * @param uuid an [UUID], can be set to a specific value if needed
 * @author Max Bossing
 * @since 0.0.1
 */
fun SkullMeta.skullTexture(base64: String, uuid: UUID = UUID.randomUUID()): SkullMeta {
    val profile = Bukkit.createProfile(uuid)
    profile.setProperty(ProfileProperty("textures", base64))
    playerProfile = profile
    return this
}