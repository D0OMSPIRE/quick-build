package me.jonathankrzeszewski.quickBuildApp.process

import me.jonathankrzeszewski.quickBuildApp.QuickBuild
import me.jonathankrzeszewski.quickBuildApp.components.ComponentLogs
import java.io.File
import javax.swing.JOptionPane

class BuildProcess( private val app: QuickBuild, private val logs: ComponentLogs, private val ideaDirectory: File, private val gradlewArguments: List<String> ) {
    private var building = false

    fun run() {
        if (building) return
        building = true

        if (gradlewArguments.isEmpty()) {
            building = false

            return JOptionPane.showMessageDialog(
                app.frame,
                "Gradle arguments are required to build and run!",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            )
        }

        logs.clear()

        val args = listOf("./gradlew.bat") + gradlewArguments
        logs.log("executing $args")

        val builder = ProcessBuilder(args)
            .directory( ideaDirectory )
            .redirectErrorStream(true)

        val process = builder.start()

        Thread {
            process.inputStream.bufferedReader().use { reader ->
                reader.forEachLine { line -> logs.log(line) }
            }

            val code = process.waitFor()
            logs.log("process ended with code: $code")

            if (code == 0) {
                done()
            } else {
                building = false
            }
        }.start()
    }

    fun done() {
        logs.log("launching app in separate terminal")

        val buildDirectory = File(ideaDirectory, "build")
        val libsDirectory = File(buildDirectory, "libs")

        if (!libsDirectory.exists()) {
            building = false
            return JOptionPane.showMessageDialog(
                app.frame,
                "Could not find \"build/libs\" in project directory",
                "Error",
                JOptionPane.ERROR_MESSAGE
            )
        }

        // read settings.gradle.kts for rootProject name to find jar name
        val settings = File(ideaDirectory, "settings.gradle.kts")
        val contents = settings.readText()
        val tokens = contents.split("rootProject.name = ")

        // write error issue here
        if (tokens.size <= 1) {
            building = false
            return JOptionPane.showMessageDialog(
                app.frame,
                "Could not determine built jar name",
                "Error",
                JOptionPane.ERROR_MESSAGE
            )
        }

        // write error issue here
        val nameQuote = tokens[1].split("\"")
        if (nameQuote.size <= 1) {
            building = false
            return JOptionPane.showMessageDialog(
                app.frame,
                "Could not determine built jar name",
                "Error",
                JOptionPane.ERROR_MESSAGE
            )
        }

        val projectJarName = nameQuote[1]
        logs.log("searching for jar possibly named \"$projectJarName\"")

        var targetJar: File? = null

        val files = libsDirectory.listFiles()
        files?.forEach { file ->
            if (file.extension != "jar") return@forEach
            if (file.name.startsWith(projectJarName)) {
                targetJar = file
            }
        }

        if (targetJar == null) {
            building = false
            return JOptionPane.showMessageDialog(
                app.frame,
                "Could not find target jar with determined name \"$projectJarName\"",
                "Error",
                JOptionPane.ERROR_MESSAGE
            )
        }

        val command = "java -jar build/libs/${targetJar.name}"
        logs.log("executing \"$command\"")

        building = false

        try {
            Thread.sleep(1000)
            ProcessBuilder("cmd", "/c", "start", "cmd", "/k", command)
                .directory(ideaDirectory)
                .start()
        } catch (e: Exception) {
            return JOptionPane.showMessageDialog(
                app.frame,
                "Failed to launch app. (${e.toString()}",
                "Error",
                JOptionPane.ERROR_MESSAGE
            )
        }
    }
}