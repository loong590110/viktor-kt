package me.julius.apps.viktor.core

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.core.View
import io.nacular.doodle.drawing.Canvas
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.event.PointerEvent
import io.nacular.doodle.event.PointerListener
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.geometry.Size
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

val View.MATCH_PARENT: Size
    get() {
        val onBoundsChanged = { _: View, _: Rectangle, new: Rectangle ->
            size = new.size
        }
        parentChange += { _, old, new ->
            old?.apply { boundsChanged -= onBoundsChanged }
            new?.apply { boundsChanged += onBoundsChanged;this@MATCH_PARENT.size = size }
        }
        if (parent != null) parent!!.boundsChanged += onBoundsChanged
        return parent?.size ?: Size(0.0, 0.0)
    }

val View.MATCH_PARENT_WIDTH: Double
    get() {
        val onBoundsChanged = { _: View, _: Rectangle, new: Rectangle ->
            width = new.width
        }
        parentChange += { _, old, new ->
            old?.apply { boundsChanged -= onBoundsChanged }
            new?.apply { boundsChanged += onBoundsChanged;this@MATCH_PARENT_WIDTH.width = width }
        }
        if (parent != null) parent!!.boundsChanged += onBoundsChanged
        return parent?.width ?: 0.0
    }

val View.MATCH_PARENT_HEIGHT: Double
    get() {
        val onBoundsChanged = { _: View, _: Rectangle, new: Rectangle ->
            height = new.height
        }
        parentChange += { _, old, new ->
            old?.apply { boundsChanged -= onBoundsChanged }
            new?.apply { boundsChanged += onBoundsChanged;this@MATCH_PARENT_HEIGHT.height = height }
        }
        if (parent != null) parent!!.boundsChanged += onBoundsChanged
        return parent?.height ?: 0.0
    }