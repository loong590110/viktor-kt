package me.julius.apps.viktor.fragments

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.core.Layout
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.drawing.Font
import io.nacular.doodle.geometry.Point
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.text.StyledText
import me.julius.apps.viktor.FONT_FAMILY
import me.julius.apps.viktor.ViktorColors.primaryColor
import me.julius.apps.viktor.ViktorColors.primaryDarkColor
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.AutomaticFragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.core.mainScope
import me.julius.apps.viktor.widgets.ImageView

class ImportColumnFragment(context: PageContext) : AutomaticFragment(context) {
    init {
        mainScope(context) {
            val title = Label(
                StyledText("IMPORT COLUMN", fontLoader {
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
            val imageSpacing = 40.0.sp
            val imageWidth = ServicesFragment.CONTENT_WIDTH / 2.0 - imageSpacing / 2.0
            val imageHeight = imageWidth * 303.0 / 580.0
            val image = ImageView(context, "images/proj_column.jpg")
            val pTitle = Label(
                StyledText("You can buy the world without going out", fontLoader {
                    size = 18.sp
                    family = FONT_FAMILY
                    weight = Font.Bold
                }, foreground = ColorPaint(Color(primaryColor)))
            )
            val p1 = Label(
                StyledText(
                    "Supported by the global network of overseas agents to effectively protect the interests of importers, VIKTOR provides customers with one-stop service of global import door-to-door.",
                    fontLoader {
                        size = 15.sp
                        family = FONT_FAMILY
                    },
                    foreground = ColorPaint(Color(primaryDarkColor))
                )
            ).apply {
                wrapsWords = true
            }
            val p2 = Label(
                StyledText(
                    "1. Ex-work services", fontLoader {
                        size = 15.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color(primaryDarkColor))
                )
            )
            val p3 = Label(
                StyledText(
                    "2. By sea/air/rail multiple modes of transportation", fontLoader {
                        size = 15.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color(primaryDarkColor))
                )
            )
            val p4 = Label(
                StyledText(
                    "3. Import customs clearance(general trade/other trade,etc.", fontLoader {
                        size = 15.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color(primaryDarkColor))
                )
            )
            val p5 = Label(
                StyledText(
                    "4. Warehousing and Distribution for import goods", fontLoader {
                        size = 15.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color(primaryDarkColor))
                )
            )
            this@ImportColumnFragment += listOf(title, subtitle, image, pTitle, p1, p2, p3, p4, p5)
            this@ImportColumnFragment.layout = Layout.simpleLayout {
                val contentMarginTop = 40.0.sp
                title.position = Point((width - title.width) / 2, 0.0)
                subtitle.position = Point((width - subtitle.width) / 2, 14.0.sp + title.bounds.bottom)
                pTitle.bounds = Rectangle(
                    (width - ServicesFragment.CONTENT_WIDTH) / 2,
                    subtitle.bounds.bottom + contentMarginTop + 20.0.sp,
                    imageWidth,
                    imageHeight
                )
                p1.bounds = Rectangle(
                    pTitle.x, pTitle.bounds.bottom + 20.0.sp, imageWidth, imageHeight
                )
                p2.bounds = Rectangle(
                    p1.x, p1.bounds.bottom + 20.0.sp, imageWidth, imageHeight
                )
                p3.bounds = Rectangle(
                    p2.x, p2.bounds.bottom + 10.0.sp, imageWidth, imageHeight
                )
                p4.bounds = Rectangle(
                    p3.x, p3.bounds.bottom + 10.0.sp, imageWidth, imageHeight
                )
                p5.bounds = Rectangle(
                    p4.x, p4.bounds.bottom + 10.0.sp, imageWidth, imageHeight
                )
                image.bounds = Rectangle(
                    (width + imageSpacing) / 2, subtitle.bounds.bottom + contentMarginTop, imageWidth, imageHeight
                )
            }
        }
    }
}