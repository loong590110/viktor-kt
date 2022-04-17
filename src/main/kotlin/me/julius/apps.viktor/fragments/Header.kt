package me.julius.apps.viktor.fragments

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.core.Container
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.drawing.Canvas
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.drawing.FontLoader
import io.nacular.doodle.drawing.LinearGradientPaint
import io.nacular.doodle.drawing.opacity
import io.nacular.doodle.geometry.Point
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.layout.constrain
import io.nacular.doodle.text.StyledText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Header(mainScope: CoroutineScope, fontLoader: FontLoader) : Container() {
    companion object {
        const val shadowSize = 8.0
    }

    private val shadowPaint by lazy {
        LinearGradientPaint(
            Color.Black opacity 0.1f, Color.Black opacity 0f, Point(0.0, height - shadowSize), Point(0.0, height)
        )
    }

    init {
        mainScope.launch {
            backgroundColor = Color.White opacity 1.0f
            val txtTitle = Label(
                StyledText(
                    "Viktor Rack & Warehouse Equipment Manufacturing Co., Ltd.", fontLoader {
                        size = 35
                        family = "Arial, Helvetica, sans-serif"
                    }, foreground = ColorPaint(Color(0xe79434u))
                )
            ).apply {
                wrapsWords = true
            }
            this@Header += listOf(txtTitle)
            layout = constrain(txtTitle) { _txtTitle ->
                val marginHorizontal = (parent.width * 0.1)
                _txtTitle.left = parent.left + marginHorizontal
                _txtTitle.top = parent.top + marginHorizontal * 0.25
                _txtTitle.right = parent.right - marginHorizontal
                this@Header.height = txtTitle.height
            }
        }
    }

    override fun render(canvas: Canvas) {
        canvas.rect(Rectangle(0.0, 0.0, width, height - shadowSize), ColorPaint(backgroundColor ?: Color.Transparent))
        if (backgroundColor?.opacity == 1f) {
            canvas.rect(Rectangle(0.0, height - shadowSize, width, shadowSize), shadowPaint)
        }
    }
}