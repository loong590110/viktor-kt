package me.julius.apps.viktor.core

import kotlinx.browser.window

object AutoSize {
    private var scale: Double = 1.0
    fun initialize(idealWidth: Double) {
        scale = window.screen.width / idealWidth
    }

    val Double.sp: Double get() = scale * this
    val Float.sp: Float get() = (scale * this).toFloat()
    val Long.sp: Long get() = (scale * this).toLong()
    val Int.sp: Int get() = (scale * this).toInt()
}