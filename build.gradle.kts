plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlinx.serialization)
    `maven-publish`
}

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlinx-serialization")
    apply(plugin = "maven-publish")
    apply(plugin = "java-library")

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


    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components.findByName("java"))

                versionMapping {
                    usage("java-api") {
                        fromResolutionOf("runtimeClasspath")
                    }
                    usage("java-runtime") {
                        fromResolutionResult()
                    }
                }

                groupId = "org.fsqrt.minekraft"
                artifactId = project.name.lowercase()
                version = project.version.toString()

                pom {
                    name.set(project.name)
                    description.set("")
                    url.set("https://github.com/fsqrt-org/Minekraft")
                    developers {
                        developer {
                            id.set("maxbossing")
                            name.set("Max Bossing")
                            email.set("max@bossi.ng")
                        }
                    }
                }
            }
        }

        repositories {
            mavenLocal()
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/fsqrt-org/Minekraft")
                credentials {
                    username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                    password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
                }
            }
        }

    }
}