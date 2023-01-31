val javaVersion = 17
val silkVersion = "1.9.5"

plugins {
    kotlin("jvm") version "1.8.0"
    id("fabric-loom") version "1.1-SNAPSHOT"
}

group = "org.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:1.19.3")
    mappings("net.fabricmc:yarn:1.19.3+build.5")
    modImplementation("net.fabricmc:fabric-loader:0.14.13")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.73.2+1.19.3")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.9.0+kotlin.1.8.0")

    modImplementation("net.silkmc:silk-core:$silkVersion")
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjdk-release=$javaVersion", "-Xskip-prerelease-check")
            jvmTarget = "$javaVersion"
        }
    }
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(javaVersion)
    }
    processResources {
        val properties = mapOf("version" to project.version)
        inputs.properties(properties)
        filesMatching("fabric.mod.json") { expand(properties) }
    }
}
