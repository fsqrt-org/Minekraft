plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlinx.serialization)
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(project(":Minekraft-Vanilla"))
}
