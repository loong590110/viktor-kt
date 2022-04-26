package me.julius.apps.viktor.fragments

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.core.container
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.core.view
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.drawing.Stroke
import io.nacular.doodle.event.PointerListener
import io.nacular.doodle.geometry.Point
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.layout.Insets
import io.nacular.doodle.layout.constrain
import io.nacular.doodle.text.StyledText
import io.nacular.doodle.utils.HorizontalAlignment
import kotlinx.coroutines.launch
import me.julius.apps.viktor.FONT_FAMILY
import me.julius.apps.viktor.ViktorColors
import me.julius.apps.viktor.ViktorColors.primaryColor
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.Fragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.core.imageLoader
import me.julius.apps.viktor.core.mainScope
import me.julius.apps.viktor.layout.LinearLayout
import me.julius.apps.viktor.widgets.GridView
import me.julius.apps.viktor.widgets.ImageView

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
                    Item("images/service1.jpg", "Light duty Racking", "MORE>"),
                    Item("images/service2.jpg", "Medium duty Racking", "MORE>"),
                    Item("images/service3.jpg", "Heavy duty Pallet racking", "MORE>"),
                    Item("images/service4.jpg", "File Compactor", "MORE>"),
                    Item("images/service5.jpg", "Cantilever Racking", "MORE>"),
                    Item("images/service6.jpg", "Mezzanine Racking", "MORE>"),
                )
            ) { _, item ->
                container {
                    mainScope.launch {
                        val image = ImageView(imageLoader.load(item.image)!!).apply {
                            bounds = Rectangle(1.0, 1.0, 220.0.sp - 2, 220.0.sp - 2)
                        }
                        val title = Label(
                            StyledText(
                                item.title, fontLoader {
                                    size = 20.sp
                                    family = FONT_FAMILY
                                }, foreground = ColorPaint(Color(ViktorColors.primaryDarkColor))
                            )
                        ).apply {
                            wrapsWords = true
                        }
                        val button = view {
                            size = Size(80.0.sp, 30.0.sp)
                            render = {
                                mainScope.launch {
                                    rect(
                                        Rectangle(0.0, 0.0, width, height), stroke = Stroke(color = Color(primaryColor))
                                    )
                                    text(
                                        item.button,
                                        font = fontLoader { size = 14.sp;family = FONT_FAMILY },
                                        at = Point(15.0.sp, 8.0.sp),
                                        fill = ColorPaint(Color(primaryColor))
                                    )
                                }
                            }
                        }
                        this@container += listOf(image, title, button)
                        var pointerEntered = false
                        this@container.pointerChanged += PointerListener.entered {
                            pointerEntered = true
                            this@container.rerender()
                        }
                        this@container.pointerChanged += PointerListener.exited {
                            pointerEntered = false
                            this@container.rerender()
                        }
                        render = {
                            val blurRadius = if (pointerEntered) 8.0.sp else 0.0.sp // 由于容器遮挡，暂看不见阴影效果
                            outerShadow(color = Color.Lightgray, blurRadius = blurRadius) {
                                rect(Rectangle(0.0, 0.0, width, height), stroke = Stroke(color = Color.Lightgray))
                            }
                        }
                        layout = constrain(image, title, button) { _image, _title, _button ->
                            _button.left = _image.right + 30.0.sp
                            _button.bottom = parent.bottom - 40.0.sp
                            _title.left = _image.right + 30.0.sp
                            _title.right = parent.right - 30.0.sp
                            _title.top = parent.top + 40.0.sp
                        }
                    }
                }
            }.apply {
                insets = Insets(20.0.sp, 0.0, 20.0, 0.0)
            }
            this@ServicesFragment += listOf(txtTitle, txtSubtitle, gridView)
            layout = LinearLayout(
                horizontalAlignment = HorizontalAlignment.Center, spacing = 10.0.sp
            )
        }
    }

    data class Item(val image: String, val title: String, val button: String)
}