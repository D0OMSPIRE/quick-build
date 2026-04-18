package me.jonathankrzeszewski.quickBuildApp.components

import me.jonathankrzeszewski.quickBuildApp.QuickBuild
import java.awt.Rectangle
import java.io.File
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.SwingConstants

class ComponentProjectSelector {
    val panel = JPanel()

    private var file: File? = null

    constructor( app: QuickBuild ) {
        panel.bounds = Rectangle( 15, 5, app.size.width - 15, 50 )
        panel.layout = null

        val title = JLabel("Project Directory")
        title.bounds = Rectangle( 0, 0, panel.width - 30, 25 )
        title.horizontalTextPosition = SwingConstants.LEFT

        val chooserButton = JButton("None")
        chooserButton.bounds = Rectangle( 0, 25, panel.width - 30, 25 )

        chooserButton.addActionListener {
            println("clicked")
        }

        panel.add(title)
        panel.add(chooserButton)
    }

    fun getDirectory(): File? {
        return file
    }
}