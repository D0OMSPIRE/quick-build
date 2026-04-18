package me.jonathankrzeszewski.quickBuildApp.components

import me.jonathankrzeszewski.quickBuildApp.QuickBuild
import java.awt.Font
import java.awt.Rectangle
import java.io.File
import javax.swing.JButton
import javax.swing.JFileChooser
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.SwingConstants

class ComponentGradlewArguments {
    val panel = JPanel()

    private var gradlew = JTextField(1)

    constructor( app: QuickBuild ) {
        panel.bounds = Rectangle( 15, 55, app.size.width - 15, 50 )
        panel.layout = null

        val title = JLabel("Gradle Arguments")
        title.bounds = Rectangle( 0, 0, panel.width - 30, 25 )
        title.horizontalTextPosition = SwingConstants.LEFT

        gradlew.bounds = Rectangle( 0, 25, panel.width - 30, 25)
        gradlew.font = Font(Font.MONOSPACED, Font.BOLD, 12)

        panel.add(title)
        panel.add(gradlew)
    }

    fun getGradleArguments(): List<String> {
        return gradlew.text.trim().split(" ").filter { it.isNotBlank() }
    }
}