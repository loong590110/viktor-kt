package me.julius.apps.viktor.core

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.core.View
import io.nacular.doodle.drawing.Canvas
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.event.PointerEvent
import io.nacular.doodle.event.PointerListener
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.text.StyledText

var Label.hoverColor: Color
    get() = throw UnsupportedOperationException()
    set(value) {
        val normalStyleText = styledText
        var hoverStyleText: StyledText? = null
        styledText.map { (text, style) ->
            StyledText(
                text, style.font, ColorPaint(value), style.background, style.decoration
            )
        }.forEach {
            hoverStyleText = if (hoverStyleText == null) it else hoverStyleText!!..it
        }
        pointerChanged += object : PointerListener {
            override fun entered(event: PointerEvent) {
                styledText = hoverStyleText!!
            }

            override fun exited(event: PointerEvent) {
                styledText = normalStyleText
            }
        }
    }

fun View.drawBackgroundColor(canvas: Canvas) {
    backgroundColor?.apply {
        canvas.rect(Rectangle(0.0, 0.0, width, height), ColorPaint(this))
    }
}