package me.julius.apps.viktor.fragments

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.core.Layout
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.geometry.Point
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.text.StyledText
import me.julius.apps.viktor.FONT_FAMILY
import me.julius.apps.viktor.ViktorColors.primaryColor
import me.julius.apps.viktor.ViktorColors.primaryDarkColor
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.AutomaticFragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.core.webview
import me.julius.apps.viktor.widgets.ImageView
import me.julius.apps.viktor.widgets.ScrollView

class ContactUsFragment(context: PageContext) : AutomaticFragment(context, height = Height.MATCH_PARENT) {
    private var onScroll: ((Double, Double) -> Unit)? = null
    private val scrollView: ScrollView

    companion object {
        private val CONTENT_WIDTH = ServicesFragment.CONTENT_WIDTH * 0.8
    }

    init {
        scrollView = ScrollView(context) {
            val banner = ImageView(context, "images/bg_contact_us.jpg")
            val title = Label(
                StyledText("CONTACT US", fontLoader {
                    size = 24.sp
                    family = FONT_FAMILY
                }, foreground = ColorPaint(Color(primaryDarkColor)))
            )
            val subtitle = Label(
                StyledText(
                    "Provide comprehensive and tailor-made storage solutions and services", fontLoader {
                        size = 14.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color(primaryDarkColor))
                )
            )
            val telephone = Label(
                StyledText("86-25-52213408", fontLoader {
                    size = 20.sp
                    family = FONT_FAMILY
                }, foreground = ColorPaint(Color(primaryColor)))
            )
            val address = Label(
                StyledText("Add: No.10 Suyuan Avenue,Nanjing,China 211122", fontLoader {
                    size = 15.sp
                    family = FONT_FAMILY
                }, foreground = ColorPaint(Color(primaryDarkColor)))
            )
            val email = Label(
                StyledText("Email: info@viktor-rack.com", fontLoader {
                    size = 15.sp
                    family = FONT_FAMILY
                }, foreground = ColorPaint(Color(primaryDarkColor)))
            )
            val qrcode = ImageView(context, "images/qrcode.jpg").apply {
                size = Size(120.0.sp)
            }
            val baiduMap = webview("BaiduMap.html").apply {
                size = Size(CONTENT_WIDTH, 260.0.sp)
            }
            val footerFragment = FooterFragment(context)
            this@ScrollView += listOf(
                banner, title, subtitle, telephone, address, email, qrcode, baiduMap, footerFragment
            )
            this@ScrollView.layout = Layout.simpleLayout {
                banner.bounds = Rectangle(0.0, HeaderFragment.HEIGHT, width, 300.0.sp)
                title.position = Point((width - title.width) / 2, 50.0.sp + banner.bounds.bottom)
                subtitle.position = Point((width - subtitle.width) / 2, 14.0.sp + title.bounds.bottom)
                telephone.position = Point((width - CONTENT_WIDTH) / 2, 60.0.sp + subtitle.bounds.bottom)
                address.position = Point((width - CONTENT_WIDTH) / 2, 14.0.sp + telephone.bounds.bottom)
                email.position = Point((width - CONTENT_WIDTH) / 2, 14.0.sp + address.bounds.bottom)
                qrcode.position = Point(
                    (width - CONTENT_WIDTH) / 2 + CONTENT_WIDTH - qrcode.width, 50.0.sp + subtitle.bounds.bottom
                )
                baiduMap.position = Point((width - CONTENT_WIDTH) / 2, 14.0.sp + qrcode.bounds.bottom)
                footerFragment.y = baiduMap.bounds.bottom + 50.0.sp
            }
        }.apply {
            setOnScrollListener { x, y ->
                onScroll?.invoke(x, y)
            }
        }
        this@ContactUsFragment += scrollView
        this@ContactUsFragment.layout = Layout.simpleLayout { scrollView.size = Size(width, height) }
    }

    fun setOnScrollListener(block: (Double, Double) -> Unit) {
        onScroll = block
    }

    fun scrollTop() {
        scrollView.scrollTo(0.0, 0.0)
    }
}