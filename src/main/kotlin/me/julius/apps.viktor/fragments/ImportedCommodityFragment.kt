package me.julius.apps.viktor.fragments

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.core.container
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.event.PointerListener
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.layout.Insets
import io.nacular.doodle.text.StyledText
import io.nacular.doodle.utils.HorizontalAlignment
import io.nacular.doodle.utils.VerticalAlignment
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

class ImportedCommodityFragment(context: PageContext) : AutomaticFragment(context) {
    init {
        mainScope(context) {
            backgroundColor = Color.Lightgray
            insets = Insets(top = 50.0.sp, bottom = 50.0.sp)
            val txtTitle = Label(StyledText("Imported Commodity", fontLoader {
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
            val spacing = Size(20.0.sp, 25.0.sp)
            val itemWidth = (totalWidth - (spanCount - 1) * spacing.width) / spanCount
            val imageHeight = itemWidth * 145.0 / 285.0
            val titleHeight = 60.0.sp
            val itemHeight = imageHeight + titleHeight
            val gridView = GridView(
                spanCount = spanCount, itemSize = Size(itemWidth, itemHeight), spacing = spacing, data = listOf(
                    Item("images/proj_alcohol.jpg", "Alcohol", 0xdd4711u),
                    Item("images/proj_dairy_products.jpg", "Dairy products", 0xdd5c0fu),
                    Item("images/proj_clothing.jpg", "Clothing", 0xe08107u),
                    Item("images/proj_cosmetic.jpg", "Cosmetic", 0xec9e23u),
                    Item("images/proj_frozen_food.jpg", "Frozen food", 0x297bdcu),
                    Item("images/proj_used_mechanical_and_electrical.jpg", "Used mechanical and electrical", 0x1f58b6u),
                    Item("images/proj_electrical.jpg", "Electrical", 0x16338eu),
                    Item("images/proj_wood.jpg", "Wood", 0x101971u),
                )
            ) { _, item ->
                container {
                    mainScope.launch {
                        val title = Label(
                            StyledText(
                                item.title, fontLoader {
                                    size = 20.sp
                                    family = FONT_FAMILY
                                }, foreground = ColorPaint(Color.White)
                            )
                        )
                        val image = ImageView(context, item.image).apply {
                            size = Size(itemWidth, imageHeight)
                        }
                        val titleContainer = container {
                            size = Size(itemWidth, titleHeight)
                            this += title
                            render = {
                                rect(Rectangle(0.0, 0.0, width, height), fill = ColorPaint(Color(item.titleBgColor)))
                            }
                            layout = LinearLayout(
                                horizontalAlignment = HorizontalAlignment.Center,
                                verticalAlignment = VerticalAlignment.Middle
                            )
                        }
                        val imageContainer = container {
                            size = Size(itemWidth, imageHeight)
                            this += image
                        } // 容器为动画特效而用（动画暂未添加）
                        this@container += listOf(titleContainer, imageContainer)
                        this@container.pointerChanged += PointerListener.entered {
                            // todo: 鼠标经过动画特效
                        }
                        this@container.pointerChanged += PointerListener.exited {
                            // todo
                        }
                        layout = LinearLayout()
                    }
                }
            }.apply {
                insets = Insets(40.0.sp, 0.0, 0.0, 0.0)
            }
            this@ImportedCommodityFragment += listOf(txtTitle, txtSubtitle, gridView)
            layout = LinearLayout(
                horizontalAlignment = HorizontalAlignment.Center, spacing = 10.0.sp
            )
        }
    }

    data class Item(val image: String, val title: String, val titleBgColor: UInt)
}