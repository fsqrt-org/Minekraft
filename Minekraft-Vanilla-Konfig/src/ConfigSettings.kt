package org.fsqrt.minekraft.config.konfig

import org.fsqrt.minekraft.annotation.MinekraftInternal
import java.nio.file.Path

/**
 * Settings for a [ConfigFile]
 * @param path A Path pointing to the ConfigFile on disk
 * @param currentVersion The current version of the file (used for migrations)
 * @param migration Migration for this file
 * @author btwonion (https://github.com/btwonion
 * @since 0.0.1
 */
@MinekraftInternal
data class ConfigSettings(
    val path: Path, val currentVersion: Int, val migration: Migration<*>
)