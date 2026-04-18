package me.jonathankrzeszewski.quickBuildApp.components

import me.jonathankrzeszewski.quickBuildApp.QuickBuild
import java.awt.Rectangle
import java.io.File
import javax.swing.JButton
import javax.swing.JFileChooser
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JPanel
import javax.swing.SwingConstants

class ComponentBuildAndRun {
    val panel = JPanel()

    constructor( app: QuickBuild, projectSelector: ComponentProjectSelector ) {
        panel.bounds = Rectangle( 15, 60, app.size.width - 15, 45 )
        panel.layout = null

        val buildAndRunButton = JButton("Build & Run")
        buildAndRunButton.bounds = Rectangle( 0, 0, panel.width - 30, panel.height )

        buildAndRunButton.addActionListener {
            val directory: File? = projectSelector.getDirectory()
            if (directory == null) {
                JOptionPane.showMessageDialog(
                    app.frame,
                    "Please select a IntelliJ Idea directory",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
                )

                return@addActionListener
            }

            // run process
        }

        panel.add(buildAndRunButton)
    }
}