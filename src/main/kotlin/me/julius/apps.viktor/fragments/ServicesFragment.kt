package me.julius.apps.viktor.fragments

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.core.container
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.core.view
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.drawing.Stroke
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.text.StyledText
import io.nacular.doodle.utils.HorizontalAlignment
import kotlinx.coroutines.launch
import me.julius.apps.viktor.FONT_FAMILY
import me.julius.apps.viktor.ViktorColors
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.Fragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.core.mainScope
import me.julius.apps.viktor.layout.LinearLayout
import me.julius.apps.viktor.widgets.GridView

class ServicesFragment(context: PageContext) : Fragment(context) {
    init {
        mainScope(context) {
            val txtTitle = Label(StyledText("—  SERVICES  —", fontLoader {
                size = 30.sp
                family = FONT_FAMILY
            }, foreground = ColorPaint(Color(ViktorColors.primaryDarkColor))))
            val txtSubtitle = Label(
                StyledText(
                    "Provide comprehensive and tailor-made storage solutions and services", fontLoader {
                        size = 14.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color(ViktorColors.primaryDarkColor))
                )
            )
            val gridView = GridView(
                spanCount = 3, itemSize = Size(400.0.sp, 220.0.sp), spacing = Size(30.0.sp, 35.0.sp), data = listOf(
                    Item("", "Hello", ""),
                    Item("", "Hello", ""),
                    Item("", "Hello", ""),
                    Item("", "Hello", ""),
                    Item("", "Hello", ""),
                    Item("", "Hello", ""),
                )
            ) { _, item ->
                container {
                    mainScope.launch {
                        val txtTitle = Label(StyledText(item.title, fontLoader {
                            size = 30.sp
                            family = FONT_FAMILY
                        }, foreground = ColorPaint(Color(ViktorColors.primaryDarkColor))))
                        this@container += listOf(txtTitle)
                        render = {
                            rect(Rectangle(0.0, 0.0, width, height), stroke = Stroke(color = Color.Lightgray))
                        }
                    }
                }
            }
            this@ServicesFragment += listOf(txtTitle, txtSubtitle, gridView)
            layout = LinearLayout.linearLayout(
                horizontalAlignment = HorizontalAlignment.Center, spacing = 10.0.sp
            ) {

            }
        }
    }

    data class Item(val image: String, val title: String, val button: String)
}