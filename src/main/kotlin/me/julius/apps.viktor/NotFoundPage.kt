package me.julius.apps.viktor

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.core.Layout
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.geometry.Point
import io.nacular.doodle.text.StyledText
import kotlinx.coroutines.launch
import me.julius.apps.viktor.core.Page
import me.julius.apps.viktor.core.PageContext

class NotFoundPage(context: PageContext) : Page(context) {
    init {
        mainScope.launch {
            plusAssign(
                Label(
                    StyledText(
                        "404", fontLoader {
                            size = 85
                            family = "Arial, Helvetica, sans-serif"
                        }, foreground = ColorPaint(Color(0xe79434u))
                    )
                )
            )
            plusAssign(
                Label(
                    StyledText("NOT FOUND", fontLoader {
                        size = 24
                        family = "Arial, Helvetica, sans-serif"
                    })
                )
            )
            layout = Layout.simpleLayout {
                val spacing = 10
                val allSpacing = spacing * (it.children.size - 1)
                val childrenHeight = it.children.sumOf { child -> child.height }
                val contentHeight = allSpacing + childrenHeight
                var y = (it.height - contentHeight) / 2
                it.children.forEach { child ->
                    val x = (it.width - child.width) / 2
                    child.position = Point(x, y)
                    y += child.height
                }
            }
        }
    }
}