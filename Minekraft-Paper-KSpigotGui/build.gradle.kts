plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.userdev)
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.adventure)

    paperweight.paperDevBundle("${libs.versions.minecraft.get()}-R0.1-SNAPSHOT")

    implementation(project(":Minekraft-Vanilla"))
    implementation(project(":Minekraft-Paper"))
}
