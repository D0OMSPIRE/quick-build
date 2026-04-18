package me.jonathankrzeszewski.log

import me.jonathankrzeszewski.log.kolor.col
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Logger {
    private var prefix = ""

    constructor( prefix: String? = null ) {
        this.prefix = prefix ?: javaClass.simpleName
    }

    private fun timestamp(): String {
        val currentTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        val timestamp = currentTime.format( formatter )
        return timestamp.col(90, 90, 180)
    }

    fun log( message: String ) {
        println("${timestamp()} ${"[$prefix]".col(120, 120, 120)} $message")
    }

    fun todo( message: String ) {
        println(message.col(90, 200, 90))
    }
}