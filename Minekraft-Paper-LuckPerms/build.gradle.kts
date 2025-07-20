plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.userdev)
}

dependencies {
    paperweight.paperDevBundle("${libs.versions.minecraft.get()}-R0.1-SNAPSHOT")

    implementation(project(":Minekraft-Vanilla"))
    compileOnly(libs.luckperms)
}
