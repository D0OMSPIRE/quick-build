plugins {
    application
    kotlin("jvm") version "2.3.10"
    id("io.github.goooler.shadow") version "8.1.8"
}

group = "me.jonathankrzeszewski"
val jarVersion = "0.0.1"
version = jarVersion

repositories { mavenCentral() }
dependencies {
    implementation("com.formdev:flatlaf:3.7.1")
}

kotlin { jvmToolchain(21) }

application {
    mainClass.set("$group.MainKt")
}

tasks.shadowJar {
    archiveBaseName.set(rootProject.name)
    archiveVersion.set(jarVersion)
    archiveClassifier.set("")

    exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA")
}