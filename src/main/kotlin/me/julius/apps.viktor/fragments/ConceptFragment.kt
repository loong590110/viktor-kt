package me.julius.apps.viktor.fragments

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.core.container
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.drawing.Stroke
import io.nacular.doodle.event.PointerListener
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.layout.Insets
import io.nacular.doodle.layout.constrain
import io.nacular.doodle.text.StyledText
import io.nacular.doodle.utils.HorizontalAlignment
import kotlinx.coroutines.launch
import me.julius.apps.viktor.FONT_FAMILY
import me.julius.apps.viktor.ViktorColors
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.AutomaticFragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.core.mainScope
import me.julius.apps.viktor.layout.LinearLayout
import me.julius.apps.viktor.widgets.GridView
import me.julius.apps.viktor.widgets.ImageView

class ConceptFragment(context: PageContext) : AutomaticFragment(context, Width.MATCH_PARENT, Height.WRAP_CONTENT) {
    init {
        mainScope(context) {
            val itemSpacing = 40.0.sp
            val itemWidth = ServicesFragment.CONTENT_WIDTH / 2.0 - itemSpacing / 2.0
            val imageWidth = itemWidth / 2.0
            val imageHeight = imageWidth * 200.0 / 300.0
            val itemHeight = imageHeight
            val gridView = GridView(
                spanCount = 2, itemSize = Size(itemWidth, itemHeight), spacing = Size(itemSpacing, 0.0), data = listOf(
                    Item("images/service_concept.jpg", "Company's Service Concept", "EAST OR WEST,Viktor IS BEST"),
                    Item(
                        "images/vision.jpg",
                        "Vision of Company",
                        "Focus on sincere cooperation, to create a first-class freight forwarding"
                    ),
                )
            ) { _, item ->
                container {
                    mainScope.launch {
                        val image = ImageView(context, item.image).apply {
                            bounds = Rectangle(1.0, 1.0, imageWidth - 2, imageHeight - 2)
                        }
                        val title = Label(
                            StyledText(
                                item.title, fontLoader {
                                    size = 18.sp
                                    family = FONT_FAMILY
                                }, foreground = ColorPaint(Color(ViktorColors.primaryDarkColor))
                            )
                        ).apply {
                            wrapsWords = true
                        }
                        val content = Label(
                            StyledText(
                                item.content, fontLoader {
                                    size = 15.sp
                                    family = FONT_FAMILY
                                }, foreground = ColorPaint(Color(ViktorColors.primaryDarkColor))
                            )
                        ).apply {
                            wrapsWords = true
                        }
                        this@container += listOf(image, title, content)
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
                        layout = constrain(image, title, content) { _image, _title, _content ->
                            _title.left = _image.right + 30.0.sp
                            _title.right = parent.right - 30.0.sp
                            _title.top = parent.top + 60.0.sp
                            _content.left = _image.right + 30.0.sp
                            _content.right = parent.right - 30.0.sp
                            _content.top = _title.bottom + 10.0.sp
                        }
                    }
                }
            }.apply {
                insets = Insets(20.0.sp, 0.0, 0.0, 0.0)
            }
            this@ConceptFragment += listOf(gridView)
            layout = LinearLayout(horizontalAlignment = HorizontalAlignment.Center)
        }
    }

    data class Item(val image: String, val title: String, val content: String)
}