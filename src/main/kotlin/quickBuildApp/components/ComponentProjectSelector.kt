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

        val chooser = JFileChooser()
        chooser.currentDirectory = File(".")
        chooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
        chooser.dialogTitle = "Choose a IntelliJ Idea directory"
        chooser.isAcceptAllFileFilterUsed = false

        chooserButton.addActionListener {
            if (chooser.showOpenDialog( this.panel ) == JFileChooser.APPROVE_OPTION) {
                file = chooser.selectedFile.absoluteFile
                file?.let {
                    var checksToPass = 0

                    file?.listFiles()?.forEach { file ->
                        when(file.name) {
                            ".idea" -> { checksToPass++ }
                            ".kotlin" -> { checksToPass++ }
                            "settings.gradle.kts" -> { checksToPass++ }
                        }
                    }

                    if (checksToPass >= 3) {
                        chooserButton.text = "${chooser.selectedFile.parentFile?.name}/${chooser.selectedFile.name}"
                    } else {
                        JOptionPane.showMessageDialog(
                            app.frame,
                            "This is not a valid IntelliJ Idea directory",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                        )

                        file = null
                        chooserButton.text = "None"
                    }
                }
            }
        }

        panel.add(title)
        panel.add(chooserButton)
    }

    fun getDirectory(): File? {
        return file
    }
}