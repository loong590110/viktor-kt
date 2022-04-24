package me.julius.apps.viktor.fragments

import io.nacular.doodle.controls.Photo
import io.nacular.doodle.core.container
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.core.view
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.drawing.opacity
import io.nacular.doodle.event.PointerListener
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.layout.constant
import io.nacular.doodle.layout.constrain
import io.nacular.doodle.utils.HorizontalAlignment
import io.nacular.doodle.utils.VerticalAlignment
import kotlinx.coroutines.launch
import me.julius.apps.viktor.ViktorColors.primaryColor
import me.julius.apps.viktor.ViktorColors.primaryDarkColor
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.Fragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.core.imageLoader
import me.julius.apps.viktor.layout.LinearLayout
import me.julius.apps.viktor.widgets.Banner

class BannerFragment(context: PageContext) : Fragment(context) {
    init {
        mainScope.launch {
            val photo1 = Photo(imageLoader.load("images/banner1.jpg")!!)
            val photo2 = Photo(imageLoader.load("images/banner2.jpg")!!)
            val photo3 = Photo(imageLoader.load("images/banner3.jpg")!!)
            val icoAngleLeft = Photo(imageLoader.load("images/angle-left.svg")!!)
            val icoAngleRight = Photo(imageLoader.load("images/angle-right.svg")!!)
            val bannerItems = listOf(photo1, photo2, photo3)
            val banner = Banner(context, bannerItems).apply {
                startAutoPlay(3000)
            }
            val indicatorSize = Size(100.0.sp, 2.0.sp)
            val indicator = view {
                var lastItem = banner.currentItem
                var fraction = 0.0
                banner.setOnScrollListener {
                    fraction = it
                    rerender()
                    if (fraction == 1.0) {
                        lastItem = banner.currentItem
                    }
                }
                val selectedColorPaint = ColorPaint(Color(primaryColor))
                val unselectedColorPaint = ColorPaint(Color(primaryDarkColor))
                val spacing = 5.0.sp
                val count = bannerItems.size
                val totalWidth = indicatorSize.width * count + spacing * (count - 1)
                val bounds = mutableListOf<Rectangle>()
                render = {
                    bounds.clear()
                    var x = width / 2 - totalWidth / 2
                    for (index in 0 until count) {
                        rect(
                            Rectangle(x, 0.0, indicatorSize.width, height).also { bounds += it },
                            indicatorSize.height,
                            unselectedColorPaint
                        )
                        x += indicatorSize.width + spacing
                    }
                    val item1 = bounds[lastItem]
                    val item2 = bounds[banner.currentItem]
                    x = item1.x + (item2.x - item1.x) * fraction
                    rect(
                        Rectangle(x, 0.0, indicatorSize.width, height), indicatorSize.height, selectedColorPaint
                    )
                }
                pointerChanged += PointerListener.clicked { e ->
                    bounds.indexOfFirst { e.location.x in it.x..it.right }.takeIf { it != -1 }
                        ?.also { banner.currentItem = it }
                }
            }
            val btnAngleLeft = container {
                size = Size(30.0.sp, 60.0.sp)
                this += icoAngleLeft.apply {
                    size = Size(20.0.sp, 20.0.sp)
                }
                layout = LinearLayout(
                    horizontalAlignment = HorizontalAlignment.Center, verticalAlignment = VerticalAlignment.Middle
                )
                render = {
                    rect(Rectangle(0.0, 0.0, width, height), fill = ColorPaint(Color(0x0u) opacity 0.3f))
                }
            }
            val btnAngleRight = container {
                size = Size(30.0.sp, 60.0.sp)
                this += icoAngleRight.apply {
                    size = Size(20.0.sp, 20.0.sp)
                }
                layout = LinearLayout(
                    horizontalAlignment = HorizontalAlignment.Center, verticalAlignment = VerticalAlignment.Middle
                )
                render = {
                    rect(Rectangle(0.0, 0.0, width, height), fill = ColorPaint(Color(0x0u) opacity 0.3f))
                }
            }
            btnAngleLeft.pointerChanged += PointerListener.clicked {
                banner.currentItem--
            }
            btnAngleRight.pointerChanged += PointerListener.clicked {
                banner.currentItem++
            }
            this@BannerFragment += listOf(banner, indicator, btnAngleLeft, btnAngleRight)
            layout = constrain(
                banner, indicator, btnAngleLeft, btnAngleRight
            ) { _banner, _indicator, _btnAngleLeft, _btnAngleRight ->
                _banner.top = parent.top
                _banner.left = parent.left
                _banner.right = parent.right
                _banner.bottom = parent.bottom
                _indicator.width = parent.width
                _indicator.height = constant(indicatorSize.height)
                _indicator.bottom = parent.bottom - 16.0.sp
                _btnAngleLeft.centerY = parent.centerY
                _btnAngleLeft.left = parent.left + 10.0.sp
                _btnAngleRight.centerY = parent.centerY
                _btnAngleRight.right = parent.right - 10.0.sp
            }
        }
    }
}