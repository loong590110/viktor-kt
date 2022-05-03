package me.julius.apps.viktor.fragments

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.core.Container
import io.nacular.doodle.core.container
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.event.PointerListener
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.layout.Insets
import io.nacular.doodle.text.StyledText
import io.nacular.doodle.utils.HorizontalAlignment
import kotlinx.coroutines.launch
import me.julius.apps.viktor.FONT_FAMILY
import me.julius.apps.viktor.ViktorColors.primaryDarkColor
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.AutomaticFragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.core.mainScope
import me.julius.apps.viktor.layout.LinearLayout
import me.julius.apps.viktor.widgets.GridView
import me.julius.apps.viktor.widgets.ImageView

class ProductListFragment(context: PageContext) : AutomaticFragment(context) {
    init {
        mainScope(context) {
            val txtTitle = Label(StyledText("—  PRODUCT CENTER  —", fontLoader {
                size = 30.sp
                family = FONT_FAMILY
            }, foreground = ColorPaint(Color(primaryDarkColor))))
            val txtSubtitle = Label(
                StyledText(
                    "Provide comprehensive and tailor-made storage solutions and services", fontLoader {
                        size = 14.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color(primaryDarkColor))
                )
            )
            val totalWidth = ServicesFragment.CONTENT_WIDTH
            val spanCount = 4
            val spacing = Size(15.0.sp, 25.0.sp)
            val itemWidth = (totalWidth - (spanCount - 1) * spacing.width) / spanCount
            val imageHeight = 190.0.sp
            val itemHeight = imageHeight + 26.0.sp
            val gridView = GridView(
                spanCount = spanCount, itemSize = Size(itemWidth, itemHeight), spacing = spacing, data = listOf(
                    Item("images/product1.jpg", "Light duty Racking"),
                    Item("images/product2.jpg", "Medium duty Racking-Type A"),
                    Item("images/product3.jpg", "Medium duty Racking-Type B"),
                    Item("images/product4.jpg", "Heavy duty Pallet racking"),
                    Item("images/product5.jpg", "File Compactor"),
                    Item("images/product6.jpg", "Cantilever Racking"),
                    Item("images/product7.jpg", "Mezzanine Racking"),
                    Item("images/product8.jpg", "Steel Structure Platform"),
                    Item("images/product9.jpg", "Two-way steel fork tray"),
                    Item("images/product10.jpg", "Steel four - way into the fork tray"),
                    Item("images/product11.jpg", "Plastic four-way into the fork single-sided use of the tray"),
                    Item("images/product12.jpg", "4-way into the use of double-sided tray"),
                )
            ) { _, item ->
                container {
                    mainScope.launch {
                        val imageContainer = Container().apply {
                            size = Size(itemWidth, imageHeight)
                        } // 容器为动画特效而用（动画暂未添加）
                        val image = ImageView(context, item.image).apply {
                            size = Size(itemWidth, imageHeight)
                        }
                        imageContainer += image
                        val title = Label(
                            StyledText(
                                item.title.takeIf { it.length > 48 }?.take(48)?.let { "$it..." } ?: item.title,
                                fontLoader {
                                    size = 14.sp
                                    family = FONT_FAMILY
                                },
                                foreground = ColorPaint(Color(primaryDarkColor))
                            )
                        )
                        this@container += listOf(imageContainer, title)
                        this@container.pointerChanged += PointerListener.entered {
                            // todo: 鼠标经过动画特效
                        }
                        this@container.pointerChanged += PointerListener.exited {
                            // todo
                        }
                        layout = LinearLayout(spacing = 10.0.sp)
                    }
                }
            }.apply {
                insets = Insets(20.0.sp, 0.0, 0.0, 0.0)
            }
            this@ProductListFragment += listOf(txtTitle, txtSubtitle, gridView)
            layout = LinearLayout(
                horizontalAlignment = HorizontalAlignment.Center, spacing = 10.0.sp
            )
        }
    }

    data class Item(val image: String, val title: String)
}