package me.julius.apps.viktor

import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.core.view
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.layout.constrain
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.MATCH_PARENT
import me.julius.apps.viktor.core.MATCH_PARENT_WIDTH
import me.julius.apps.viktor.core.Page
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.core.animate
import me.julius.apps.viktor.fragments.AboutViktorFragment
import me.julius.apps.viktor.fragments.ContactUsFragment
import me.julius.apps.viktor.fragments.FloatMenu
import me.julius.apps.viktor.fragments.HeaderFragment
import me.julius.apps.viktor.fragments.HomeFragment
import me.julius.apps.viktor.fragments.ProductsFragment
import me.julius.apps.viktor.fragments.ProjectCaseFragment
import me.julius.apps.viktor.fragments.QQCard
import me.julius.apps.viktor.fragments.TelephoneCard
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
        val shadow = view {
            visible = false
            val shadowHeight = 4.0.sp
            bounds = Rectangle(0.0, 0.0, MATCH_PARENT_WIDTH, header.height + shadowHeight * 3)
            render = {
                outerShadow(vertical = shadowHeight, blurRadius = shadowHeight, color = Color.Black) {
                    rect(Rectangle(0.0, 0.0, width, header.height), fill = ColorPaint(Color.White))
                }
            }
        }
        val telephoneCard = TelephoneCard(context)
        val qqCard = QQCard(context)
        val floatMenu = FloatMenu(context) { index, show ->
            when (index) {
                0 -> {
                    if (show) {
                        telephoneCard.animate().translationX(bounds.x - telephoneCard.width).start()
                    } else {
                        telephoneCard.animate().translationX(bounds.right).start()
                    }
                }
                1 -> {
                    if (show) {
                        qqCard.animate().translationX(bounds.x - qqCard.width).start()
                    } else {
                        qqCard.animate().translationX(bounds.right).start()
                    }
                }
                2 -> {}
                3 -> {}
            }
        }.apply {
            boundsChanged += { _, _, new ->
                telephoneCard.x = new.right
                telephoneCard.y = new.y - (telephoneCard.height - width) / 2 // width = height of item
                qqCard.x = new.right
                qqCard.y = new.y - (qqCard.height - width) / 2 + width // 下移一格
            }
        }
        this += listOf(viewPager, shadow, header, telephoneCard, qqCard, floatMenu)
        layout = constrain(floatMenu) { _floatMenu ->
            _floatMenu.right = parent.right
            _floatMenu.centerY = parent.centerY
        }
    }
}