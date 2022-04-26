package me.julius.apps.viktor.fragments

import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.layout.constrain
import io.nacular.doodle.utils.HorizontalAlignment
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.Fragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.layout.LinearLayout
import me.julius.apps.viktor.widgets.ScrollView

class HomeFragment(context: PageContext) : Fragment(context) {
    private var onScroll: ((Double, Double) -> Unit)? = null
    private val scrollView: ScrollView

    init {
        scrollView = ScrollView(context) {
            val bannerFragment = BannerFragment(context)
            val servicesFragment = ServicesFragment(context)
            val casesFragment = CasesFragment(context)
            val footerFragment = FooterFragment(context)
            this@ScrollView += listOf(bannerFragment, servicesFragment, casesFragment, footerFragment)
            layout = LinearLayout.linearLayout(
                horizontalAlignment = HorizontalAlignment.Center, spacing = 50.0.sp
            ) {
                bannerFragment.size = Size(it.width, 550.0.sp)
            }
        }.apply {
            setOnScrollListener { x, y ->
                onScroll?.invoke(x, y)
            }
        }
        this@HomeFragment += scrollView
        layout = constrain(scrollView) {
            it.width = parent.width
            it.height = parent.height
        }
    }

    fun setOnScrollListener(block: (Double, Double) -> Unit) {
        onScroll = block
    }

    fun scrollTop() {
        scrollView.scrollTo(0.0, 0.0)
    }
}