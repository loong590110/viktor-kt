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
import me.julius.apps.viktor.fragments.NavigationCard
import me.julius.apps.viktor.fragments.ProductsFragment
import me.julius.apps.viktor.fragments.ProjectCaseFragment
import me.julius.apps.viktor.fragments.QQCard
import me.julius.apps.viktor.fragments.QRCodeCard
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
        val phCard = TelephoneCard(context)
        val qqCard = QQCard(context)
        val qrCard = QRCodeCard(context)
        val nvCard = NavigationCard(context)
        val floatMenu = FloatMenu(context) { index, show ->
            when (index) {
                0 -> {
                    if (show) {
                        phCard.animate().translationX(bounds.x - phCard.width).start()
                    } else {
                        phCard.animate().translationX(bounds.right).start()
                    }
                }
                1 -> {
                    if (show) {
                        qqCard.animate().translationX(bounds.x - qqCard.width).start()
                    } else {
                        qqCard.animate().translationX(bounds.right).start()
                    }
                }
                2 -> {
                    if (show) {
                        qrCard.animate().translationX(bounds.x - qrCard.width).start()
                    } else {
                        qrCard.animate().translationX(bounds.right).start()
                    }
                }
                3 -> {
                    if (show) {
                        nvCard.animate().translationX(bounds.x - nvCard.width).start()
                    } else {
                        nvCard.animate().translationX(bounds.right).start()
                    }
                }
            }
        }.apply {
            boundsChanged += { _, _, new ->
                phCard.x = new.right
                phCard.y = new.y - (phCard.height - width) / 2 // width = height of item
                qqCard.x = new.right
                qqCard.y = new.y - (qqCard.height - width) / 2 + width // 下移一格
                qrCard.x = new.right
                qrCard.y = new.y - (qrCard.height - width) / 2 + width * 2 // 下移两格
                nvCard.x = new.right
                nvCard.y = new.y - (nvCard.height - width) / 2 + width * 3 // 下移三格
            }
        }
        this += listOf(viewPager, shadow, header, phCard, qqCard, qrCard, nvCard, floatMenu)
        layout = constrain(floatMenu) { _floatMenu ->
            _floatMenu.right = parent.right
            _floatMenu.centerY = parent.centerY
        }
    }
}