package me.julius.apps.viktor

import io.nacular.doodle.core.View
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.core.view
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.layout.constant
import io.nacular.doodle.layout.constrain
import me.julius.apps.viktor.core.AutoSize.sp
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
    private lateinit var shadow: View

    init {
        val homeFragment: HomeFragment
        val viewPager = ViewPager(
            context, listOf(
                HomeFragment(context).apply {
                    setOnScrollListener { _, y ->
                        shadow.visible = y != 0.0
                    }
                    homeFragment = this
                },
                AboutViktorFragment(context),
                ProductsFragment(context),
                ProjectCaseFragment(context),
                ContactUsFragment(context)
            )
        )
        val header = HeaderFragment(context) {
            viewPager.currentItem = it
        }
        val shadowHeight = 8.0.sp
        shadow = view {
            visible = false
            render = {
                outerShadow(blurRadius = shadowHeight, color = Color.Gray) {
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
            setOnScrollTopListener {
                homeFragment.scrollTop()
            }
        }
        this += listOf(viewPager, shadow, header, phCard, qqCard, qrCard, nvCard, floatMenu)
        layout = constrain(floatMenu, viewPager, shadow, header) { _floatMenu, _viewPager, _shadow, _header ->
            _floatMenu.right = parent.right
            _floatMenu.centerY = parent.centerY
            _viewPager.width = parent.width
            _viewPager.height = parent.height
            _header.width = parent.width
            _header.height = constant(136.0.sp)
            _shadow.width = parent.width
            _shadow.height = _header.height + shadowHeight * 2
        }
    }
}