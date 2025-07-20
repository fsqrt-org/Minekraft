plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlinx.serialization)
}

repositories {
    mavenCentral()
}

allprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlinx-serialization")

    group = "org.fsqrt.minekraft"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }

    java {
        toolchain {
            sourceCompatibility = JavaVersion.VERSION_24
            targetCompatibility = JavaVersion.VERSION_24
            languageVersion.set(JavaLanguageVersion.of(24))
        }
    }

    kotlin {
        jvmToolchain(24)
        sourceSets {
            main {
                kotlin.srcDirs("src")
            }
        }
    }
}