package me.julius.apps.viktor.fragments

import io.nacular.doodle.core.Layout
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.geometry.Size
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.AutomaticFragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.widgets.ImageView
import me.julius.apps.viktor.widgets.ScrollView

class ProjectCaseFragment(context: PageContext) : AutomaticFragment(context, height = Height.MATCH_PARENT) {
    private var onScroll: ((Double, Double) -> Unit)? = null
    private val scrollView: ScrollView

    init {
        scrollView = ScrollView(context) {
            val banner = ImageView(context, "images/bg_project_case.jpg")
            val importColumnFragment = ImportColumnFragment(context)
            val importedCommodityFragment = ImportedCommodityFragment(context)
            val importServicesFragment = ImportServicesFragment(context)
            val footerFragment = FooterFragment(context)
            this@ScrollView += listOf(
                banner,
                importColumnFragment,
                importedCommodityFragment,
                importServicesFragment,
                footerFragment
            )
            this@ScrollView.layout = Layout.simpleLayout {
                banner.bounds = Rectangle(0.0, HeaderFragment.HEIGHT, width, 300.0.sp)
                importColumnFragment.y = banner.bounds.bottom + 50.0.sp
                importedCommodityFragment.y = importColumnFragment.bounds.bottom + 50.0.sp
                importServicesFragment.y = importedCommodityFragment.bounds.bottom + 50.0.sp
                footerFragment.y = importServicesFragment.bounds.bottom + 50.0.sp
            }
        }.apply {
            setOnScrollListener { x, y ->
                onScroll?.invoke(x, y)
            }
        }
        this@ProjectCaseFragment += scrollView
        this@ProjectCaseFragment.layout = Layout.simpleLayout { scrollView.size = Size(width, height) }
    }

    fun setOnScrollListener(block: (Double, Double) -> Unit) {
        onScroll = block
    }

    fun scrollTop() {
        scrollView.scrollTo(0.0, 0.0)
    }
}