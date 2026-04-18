package me.jonathankrzeszewski.quickBuildApp

import com.formdev.flatlaf.FlatDarkLaf
import me.jonathankrzeszewski.log.Logger
import me.jonathankrzeszewski.quickBuildApp.components.ComponentBuildAndRun
import me.jonathankrzeszewski.quickBuildApp.components.ComponentProjectSelector
import java.awt.Dimension
import javax.imageio.ImageIO
import javax.swing.JFrame
import javax.swing.UIManager

val Logger = Logger("QuickBuild-App")

class QuickBuild {
    private val title = "Quick Builder"
    val size = Dimension(350, 160)

    val frame = JFrame()

    constructor() {
        init()
    }

    private fun init() {
        initComponents()
        initSystem()
        initWindow()
    }

    private fun initComponents() {
        Logger.log("initializing components")

        val projectSelector = ComponentProjectSelector( this )
        val buildAndRun = ComponentBuildAndRun( this, projectSelector )

        frame.add( projectSelector.panel )
        frame.add( buildAndRun.panel )
    }

    private fun initSystem() {
        Logger.log("initializing system")

        try {
            UIManager.setLookAndFeel( FlatDarkLaf() )
        } catch (e: Exception) {
            Logger.log("Failed to initialize theme (${e.toString()})")
        }
    }

    private fun initWindow() {
        Logger.log("creating window")

        frame.title = title
        frame.iconImage = ImageIO.read(javaClass.getResourceAsStream("/icon.png"))
        frame.size = size
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isResizable = false
        frame.layout = null
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
    }
}