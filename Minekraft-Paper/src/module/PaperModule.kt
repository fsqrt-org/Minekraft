package org.fsqrt.minekraft.paper.module

import org.fsqrt.minekraft.annotation.MinekraftInternal
import org.fsqrt.minekraft.module.VanillaModule
import org.fsqrt.minekraft.paper.Minekraft

/**
 * Module Entrypoint for Paper Modules
 * @see VanillaModule
 * @author Max Bossing
 * @since 0.0.1
 */
@OptIn(MinekraftInternal::class)
interface PaperModule : VanillaModule<Minekraft>