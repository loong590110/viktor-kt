package me.julius.apps.viktor.fragments

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.core.Layout
import io.nacular.doodle.core.container
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.geometry.Point
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.text.StyledText
import io.nacular.doodle.utils.HorizontalAlignment
import me.julius.apps.viktor.FONT_FAMILY
import me.julius.apps.viktor.ViktorColors.primaryColor
import me.julius.apps.viktor.ViktorColors.primaryDarkColor
import me.julius.apps.viktor.ViktorColors.thirdDarkColor
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.AutomaticFragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.core.imageLoader
import me.julius.apps.viktor.core.mainScope
import me.julius.apps.viktor.layout.LinearLayout
import me.julius.apps.viktor.widgets.ImageView

class ContactsFragment(context: PageContext) : AutomaticFragment(context, Width.MATCH_PARENT, Height.WRAP_CONTENT) {
    init {
        backgroundColor = Color(primaryDarkColor)
        this@ContactsFragment += container {
            mainScope(context) {
                this@container.size = Size(ServicesFragment.CONTENT_WIDTH, 150.0.sp)
                val phone = ImageView(imageLoader.load("images/phone.png")!!)
                val wechat = ImageView(imageLoader.load("images/qrcode.jpg#footer")!!) // 附加信息以以区分key来创建新实例
                val title = Label(
                    StyledText(
                        "24-hour service hotline:", fontLoader {
                            size = 16.sp
                            family = FONT_FAMILY
                        }, foreground = ColorPaint(Color(thirdDarkColor))
                    )
                )
                val phoneNum = Label(
                    StyledText(
                        "86-25-52213408", fontLoader {
                            size = 24.sp
                            family = FONT_FAMILY
                        }, foreground = ColorPaint(Color(primaryColor))
                    )
                )
                val address = Label(
                    StyledText(
                        "Address: No.10 Suyuan Avenue,Nanjing,China 211122", fontLoader {
                            size = 16.sp
                            family = FONT_FAMILY
                        }, foreground = ColorPaint(Color(thirdDarkColor))
                    )
                )
                val email = Label(
                    StyledText(
                        "Email: info@viktor-rack.com", fontLoader {
                            size = 16.sp
                            family = FONT_FAMILY
                        }, foreground = ColorPaint(Color(thirdDarkColor))
                    )
                )
                val wechatTitle = Label(
                    StyledText(
                        "Sweeping attention Wechat QR code", fontLoader {
                            size = 16.sp
                            family = FONT_FAMILY
                        }, foreground = ColorPaint(Color(thirdDarkColor))
                    )
                ).apply {
                    wrapsWords = true
                }
                this@container += listOf(phone, title, phoneNum, address, email, wechat, wechatTitle)
                layout = Layout.simpleLayout {
                    phone.bounds = Rectangle(0.0, 25.0.sp, 35.0.sp, 35.0.sp)
                    title.position = Point(phone.width + 10.0.sp, phone.height / 2 - 8.sp + phone.y)
                    phoneNum.position = Point(title.bounds.right, title.bounds.bottom - 25.sp)
                    address.position = Point(phone.x, phone.bounds.bottom + 20.sp)
                    email.position = Point(phone.x, address.bounds.bottom + 6.sp)
                    wechatTitle.bounds = Rectangle(this@container.width - 150.sp, 65.0.sp, 150.0.sp, 100.0.sp)
                    wechat.bounds = Rectangle(wechatTitle.x - 110.0.sp, 25.0.sp, 100.0.sp, 100.0.sp)
                }
            }
        }
        layout = LinearLayout(horizontalAlignment = HorizontalAlignment.Center)
    }
}