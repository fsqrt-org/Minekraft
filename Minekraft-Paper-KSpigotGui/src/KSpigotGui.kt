package org.fsqrt.minekraft.paper.gui.kspigot

import org.fsqrt.minekraft.annotation.MinekraftInternal
import org.fsqrt.minekraft.paper.Minekraft
import org.fsqrt.minekraft.paper.module.PaperModule

@OptIn(MinekraftInternal::class)
object KSpigotGUI : PaperModule {
    private val guiHolderProperty = lazy { GUIHolder }
    internal val guiHolder by guiHolderProperty

    override fun load(entrypoint: Minekraft) {}

    override fun start(entrypoint: Minekraft) {}

    override fun stop(entrypoint: Minekraft) {
        if (guiHolderProperty.isInitialized()) { guiHolderProperty.value.close() }
    }
}