package me.julius.apps.viktor.fragments

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.core.container
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.layout.Insets
import io.nacular.doodle.text.StyledText
import io.nacular.doodle.utils.HorizontalAlignment
import io.nacular.doodle.utils.Orientation
import io.nacular.doodle.utils.VerticalAlignment
import kotlinx.coroutines.launch
import me.julius.apps.viktor.FONT_FAMILY
import me.julius.apps.viktor.ViktorColors.primaryDarkColor
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.AutomaticFragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.core.imageLoader
import me.julius.apps.viktor.core.mainScope
import me.julius.apps.viktor.layout.LinearLayout
import me.julius.apps.viktor.widgets.GridView
import me.julius.apps.viktor.widgets.ImageView

class FeaturesFragment(context: PageContext) : AutomaticFragment(context, Width.MATCH_PARENT, Height.WRAP_CONTENT) {
    init {
        mainScope(context) {
            backgroundColor = Color.Lightgray
            val totalWidth = ServicesFragment.CONTENT_WIDTH
            val spanCount = 5
            val spacing = Size(10.0.sp)
            val itemWidth = (totalWidth - (spanCount - 1) * spacing.width) / spanCount
            val itemHeight = 60.0.sp
            val gridView = GridView(
                spanCount = spanCount, itemSize = Size(itemWidth, itemHeight), spacing = spacing, data = listOf(
                    Item("images/equipment.png", "First class transportation equipment"),
                    Item("images/team.png", "Excellent service team"),
                    Item("images/system.png", "Top transportation system"),
                    Item("images/experience.png", "Rich transportation experience"),
                    Item("images/price.png", "Reasonable Price"),
                )
            ) { _, item ->
                container {
                    mainScope.launch {
                        val image = ImageView(imageLoader.load(item.image)!!).apply {
                            bounds = Rectangle(0.0, 0.0, itemHeight, itemHeight)
                        }
                        val title = Label(
                            StyledText(
                                item.title, fontLoader {
                                    size = 16.sp
                                    family = FONT_FAMILY
                                }, foreground = ColorPaint(Color(primaryDarkColor))
                            )
                        ).apply {
                            wrapsWords = true
                            width = itemWidth - itemHeight - 10.0.sp
                        }
                        this@container += listOf(image, title)
                        layout = LinearLayout(
                            orientation = Orientation.Horizontal,
                            verticalAlignment = VerticalAlignment.Middle,
                            spacing = 10.0.sp
                        )
                    }
                }
            }.apply {
                insets = Insets(20.0.sp, 0.0, 20.0.sp, 0.0)
            }
            this@FeaturesFragment += gridView
            layout = LinearLayout(horizontalAlignment = HorizontalAlignment.Center)
        }
    }

    data class Item(val image: String, val title: String)
}