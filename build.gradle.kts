val javaVersion = 17
val silkVersion = "1.9.8"

plugins {
    kotlin("jvm") version "1.8.20"
    id("fabric-loom") version "1.1-SNAPSHOT"
}

group = "org.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:1.19.4")
    mappings("net.fabricmc:yarn:1.19.4+build.2")
    modImplementation("net.fabricmc:fabric-loader:0.14.19")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.78.0+1.19.4")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.9.3+kotlin.1.8.20")

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
