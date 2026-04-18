package me.jonathankrzeszewski.quickBuildApp.process

import me.jonathankrzeszewski.quickBuildApp.QuickBuild
import me.jonathankrzeszewski.quickBuildApp.components.ComponentLogs
import java.io.File
import javax.swing.JOptionPane

class BuildProcess( private val app: QuickBuild, private val logs: ComponentLogs, private val ideaDirectory: File, private val gradlewArguments: List<String> ) {
    fun run() {
        if (gradlewArguments.isEmpty()) {
            return JOptionPane.showMessageDialog(
                app.frame,
                "Gradle arguments are required to build and run!",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            )
        }

        logs.clear()
        logs.log(gradlewArguments.joinToString(" "))

        /*/
        val buildDirectory = File(ideaDirectory, "build")
        val libsDirectory = File(ideaDirectory, "libs")

        val buildCommand = tokens[1].trim()
        val gradlew = File(ideaDirectory, "gradlew").absolutePath
        val command = listOf(gradlew, "clean", buildCommand)

        val builder = ProcessBuilder("cmd", "/c", "start", "cmd", "/k", command.joinToString(" "))
        builder.directory(ideaDirectory)

        val process = builder.start()
        process.waitFor()
         */
    }
}