package me.julius.apps.viktor.fragments

import io.nacular.doodle.core.container
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.LinearGradientPaint
import io.nacular.doodle.geometry.Point
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.geometry.Size
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.Fragment
import me.julius.apps.viktor.core.MATCH_PARENT
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.layout.LinearLayout
import me.julius.apps.viktor.widgets.ScrollView

class HomeFragment(context: PageContext) : Fragment(context) {
    private var onScroll: ((Double, Double) -> Unit)? = null

    init {
        val scrollView = ScrollView {
            val bannerFragment = BannerFragment(context)
            val footer = container {
                size = Size(500.0.sp, 20000.0.sp)
                render = {
                    rect(
                        Rectangle(0.0, 0.0, width, height),
                        fill = LinearGradientPaint(Color.Gray, Color.Black, Point(0.0, 0.0), Point(width, height))
                    )
                }
            }
            this += listOf(bannerFragment, footer)
            layout = LinearLayout.linearLayout {
                bannerFragment.size = Size(it.width, 550.0.sp)
            }
        }.apply {
            size = MATCH_PARENT
            setOnScrollListener { x, y ->
                onScroll?.invoke(x, y)
            }
        }
        this@HomeFragment += scrollView
    }

    fun setOnScrollListener(block: (Double, Double) -> Unit) {
        onScroll = block
    }
}