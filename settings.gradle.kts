dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "Minekraft"

// Noarch
include(":Minekraft-Vanilla")
include(":Minekraft-Vanilla-Konfig")

// Paper
include(":Minekraft-Paper")
include(":Minekraft-Paper-KSpigotGui")
include(":Minekraft-Paper-LuckPerms")