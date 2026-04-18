package me.jonathankrzeszewski

import com.formdev.flatlaf.FlatDarkLaf
import me.jonathankrzeszewski.log.Logger
import me.jonathankrzeszewski.quickBuildApp.QuickBuild
import javax.swing.SwingUtilities

val Logger = Logger("QuickBuild-Main")

fun main() {
    // TODO LOG
    val file = object {}.javaClass.getResourceAsStream("/todo.txt")
    if (file != null) {
        file.bufferedReader().use { reader ->
            reader.readLines().forEach { line ->
                Logger.todo(line)
            }
            println("")
        }
    }

    Logger.log("initializing quick-build app")

    SwingUtilities.invokeLater {
        FlatDarkLaf.setup()
        QuickBuild()
    }
}