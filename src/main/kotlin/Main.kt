package me.jonathankrzeszewski

import com.formdev.flatlaf.FlatDarkLaf
import me.jonathankrzeszewski.log.Logger
import me.jonathankrzeszewski.quickBuildApp.QuickBuild
import javax.swing.SwingUtilities

val Logger = Logger("QuickBuild-Main")

fun main() {
    Logger.log("initializing quick-build app")

    SwingUtilities.invokeLater {
        FlatDarkLaf.setup()
        QuickBuild()
    }
}