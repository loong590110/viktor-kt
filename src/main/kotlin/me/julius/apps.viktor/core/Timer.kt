package me.julius.apps.viktor.core

import kotlinx.browser.window

class Timer(private val interval: Int, private val block: () -> Unit) {
    private var handle: Int = 0
    val isStarted get() = handle > 0
    fun cancel(): Unit = if (handle > 0) window.clearInterval(handle).also { handle = 0 } else Unit
    fun start(): Unit = cancel().also { window.setInterval(block, interval).also { handle = it } }
}