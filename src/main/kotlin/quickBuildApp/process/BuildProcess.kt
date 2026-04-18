package me.jonathankrzeszewski.quickBuildApp.process

import me.jonathankrzeszewski.quickBuildApp.QuickBuild
import java.io.File
import javax.swing.JOptionPane

class BuildProcess( private val app: QuickBuild, private val ideaDirectory: File ) {
    private val gradleSettings: File = File(ideaDirectory, "settings.gradle.kts")

    fun run() {
        val contents = gradleSettings.readText()
        val tokens = contents.split("// quick-build=")

        if (tokens.size <= 1) {
            JOptionPane.showMessageDialog(
                app.frame,
                "Could not find \"// quick-build=BUILD_ARG\" in settings.gradle.kts, add to file and replace BUILD_ARG with your build command!",
                "Error",
                JOptionPane.ERROR_MESSAGE
            )

            return
        }

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