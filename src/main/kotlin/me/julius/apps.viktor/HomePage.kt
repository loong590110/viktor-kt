package me.julius.apps.viktor

import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.geometry.Size
import me.julius.apps.viktor.core.AutoSize.sp
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
            listOf(
                HomeFragment(context),
                AboutViktorFragment(context),
                ProductsFragment(context),
                ProjectCaseFragment(context),
                ContactUsFragment(context)
            )
        ).apply {
            size = this@HomePage.size
        }
        val header = HeaderFragment(context) {
            viewPager.currentItem = it
        }.also {
            it.size = Size(this@HomePage.size.width, 150.0.sp)
        }
        this += listOf(viewPager, header)
    }
}