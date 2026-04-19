package me.jonathankrzeszewski.quickBuildApp.components

import me.jonathankrzeszewski.quickBuildApp.QuickBuild
import java.awt.Color
import java.awt.Rectangle
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextArea
import javax.swing.border.LineBorder

class ComponentLogs {
    val panel = JPanel()

    private var logs: JTextArea? = null

    constructor( app: QuickBuild ) {
        panel.bounds = Rectangle( 15, 170, app.size.width - 15, 180 )
        panel.layout = null

        logs = JTextArea(10, 10)
        logs?.bounds = Rectangle(0, 0, app.size.width - 45, panel.height)
        logs?.isEditable = false
        logs?.lineWrap = true
        logs?.wrapStyleWord = true
        logs?.border = LineBorder( Color.GRAY, 2, true )

        val scroll = JScrollPane(logs)
        scroll.bounds = logs?.bounds as Rectangle
        scroll.autoscrolls = true

        panel.add(scroll)
    }

    fun log( message: String ) {
        logs?.text += "$message\n"
        logs?.caretPosition = logs?.document?.length ?: 0
    }

    fun clear() {
        logs?.text = ""
    }
}