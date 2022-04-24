package me.julius.apps.viktor

import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.geometry.Size
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.MATCH_PARENT
import me.julius.apps.viktor.core.MATCH_PARENT_WIDTH
import me.julius.apps.viktor.core.Page
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.fragments.AboutViktorFragment
import me.julius.apps.viktor.fragments.ContactUsFragment
import me.julius.apps.viktor.fragments.HeaderFragment
import me.julius.apps.viktor.fragments.HomeFragment
import me.julius.apps.viktor.fragments.ProductsFragment
import me.julius.apps.viktor.fragments.ProjectCaseFragment
import me.julius.apps.viktor.widgets.ViewPager

class HomePage(context: PageContext) : Page(context) {
    init {
        val viewPager = ViewPager(
            context, listOf(
                HomeFragment(context),
                AboutViktorFragment(context),
                ProductsFragment(context),
                ProjectCaseFragment(context),
                ContactUsFragment(context)
            )
        ).apply {
            size = MATCH_PARENT
        }
        val header = HeaderFragment(context) {
            viewPager.currentItem = it
        }.apply {
            size = Size(MATCH_PARENT_WIDTH, 136.0.sp)
        }
        this += listOf(viewPager, header)
    }
}