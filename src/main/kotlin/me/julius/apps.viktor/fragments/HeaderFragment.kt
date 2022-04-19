package me.julius.apps.viktor.fragments

import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.drawing.Canvas
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.drawing.LinearGradientPaint
import io.nacular.doodle.drawing.opacity
import io.nacular.doodle.geometry.Point
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.layout.constrain
import io.nacular.doodle.text.StyledText
import kotlinx.coroutines.launch
import me.julius.apps.viktor.core.Fragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.widgets.AutoSizeLabel

class HeaderFragment(context: PageContext) : Fragment(context) {
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
            val txtTitle = AutoSizeLabel(
                StyledText(
                    "Viktor Rack & Warehouse Equipment Manufacturing Co., Ltd.", fontLoader {
                        size = 35
                        family = "Arial, Helvetica, sans-serif"
                    }, foreground = ColorPaint(Color(0xe79434u))
                )
            ).apply {
                wrapsWords = true
            }
            this@HeaderFragment += listOf(txtTitle)
            layout = constrain(txtTitle) { _txtTitle ->
                val marginHorizontal = (parent.width * 0.1)
                _txtTitle.left = parent.left + marginHorizontal
                _txtTitle.top = parent.top + marginHorizontal * 0.25
                _txtTitle.right = parent.right - marginHorizontal
                _txtTitle.bottom = parent.bottom - 50
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