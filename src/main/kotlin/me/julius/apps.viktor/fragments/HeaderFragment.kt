package me.julius.apps.viktor.fragments

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.core.container
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.drawing.opacity
import io.nacular.doodle.event.PointerListener
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.layout.constrain
import io.nacular.doodle.text.StyledText
import io.nacular.doodle.utils.HorizontalAlignment
import io.nacular.doodle.utils.Orientation
import io.nacular.doodle.utils.VerticalAlignment
import kotlinx.coroutines.launch
import me.julius.apps.viktor.FONT_FAMILY
import me.julius.apps.viktor.ViktorColors.primaryColor
import me.julius.apps.viktor.ViktorColors.primaryDarkColor
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.Fragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.core.hoverColor
import me.julius.apps.viktor.layout.LinearLayout

class HeaderFragment(context: PageContext, block: (Int) -> Unit) : Fragment(context) {
    companion object {
        val HEIGHT = 136.0.sp
    }

    init {
        mainScope.launch {
            backgroundColor = Color.White opacity 0.8f
            val txtTitle = Label(
                StyledText(
                    "Viktor Rack & Warehouse Equipment Manufacturing Co., Ltd.", fontLoader {
                        size = 35.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color(primaryColor))
                )
            )
            val tabMenu = container {
                var curIndex = 0
                listOf("HOME", "ABOUT VIKTOR", "PRODUCTS", "PROJECT CASE", "CONTACT US").forEachIndexed { index, text ->
                    mainScope.launch {
                        this@container += Label(
                            StyledText(
                                text, fontLoader {
                                    size = 16.sp
                                    family = FONT_FAMILY
                                }, foreground = ColorPaint(Color(primaryDarkColor))
                            )
                        ).apply {
                            hoverColor = Color(primaryColor)
                            pointerChanged += PointerListener.clicked {
                                curIndex = index
                                this@container.rerender()
                                block(index)
                            }
                        }
                    }
                }
                layout = LinearLayout(
                    orientation = Orientation.Horizontal,
                    horizontalAlignment = HorizontalAlignment.Center,
                    verticalAlignment = VerticalAlignment.Middle
                )
                render = lambda@{
                    if (this@container.children.size <= curIndex) {
                        return@lambda
                    }
                    val tab = this@container.children[curIndex]
                    rect(Rectangle(tab.x, height - 2.0.sp, tab.width, 2.0.sp), fill = ColorPaint(Color(primaryColor)))
                }
            }
            this@HeaderFragment += listOf(txtTitle, tabMenu)
            layout = constrain(txtTitle, tabMenu) { _txtTitle, _tabMenu ->
                val marginHorizontal = (parent.width * 0.14)
                _txtTitle.left = parent.left + marginHorizontal
                _txtTitle.top = parent.top + 36.sp
                _tabMenu.left = _txtTitle.left
                _tabMenu.top = _txtTitle.bottom + 14.sp
                _tabMenu.right = _txtTitle.right
                _tabMenu.bottom = parent.bottom
            }
        }
    }
}