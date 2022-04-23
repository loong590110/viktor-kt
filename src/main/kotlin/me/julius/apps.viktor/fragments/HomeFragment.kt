package me.julius.apps.viktor.fragments

import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.geometry.Size
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.Fragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.layout.LinearLayout

class HomeFragment(context: PageContext) : Fragment(context) {
    init {
        val bannerFragment = BannerFragment(context)
        this@HomeFragment += listOf(bannerFragment)
        layout = LinearLayout.linearLayout {
            bannerFragment.size = Size(it.width, 500.0.sp)
        }
    }
}