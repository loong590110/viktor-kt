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

class ImportServicesFragment(context: PageContext) : AutomaticFragment(context) {
    init {
        mainScope(context) {
            val title = Label(
                StyledText("Other import services", fontLoader {
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
            val image = ImageView(context, "images/proj_services.jpg")
            val pTitle = Label(
                StyledText("General trade import agent", fontLoader {
                    size = 18.sp
                    family = FONT_FAMILY
                    weight = Font.Bold
                }, foreground = ColorPaint(Color(primaryColor)))
            )
            val p1 = Label(
                StyledText(
                    "VIKTOR Company can provide general trade agent import services for enterprises or individuals who do not have import and export rights, and those that have import and export rights but do not have relevant business qualifications.",
                    fontLoader {
                        size = 15.sp
                        family = FONT_FAMILY
                    },
                    foreground = ColorPaint(Color(primaryDarkColor))
                )
            ).apply {
                wrapsWords = true
            }
            val pTitle2 = Label(
                StyledText("Import documents", fontLoader {
                    size = 18.sp
                    family = FONT_FAMILY
                    weight = Font.Bold
                }, foreground = ColorPaint(Color(primaryColor)))
            )
            val p2 = Label(
                StyledText(
                    "VIKTOR can provide professional consulting services and document handling, solve problems quickly for customers and ensure smooth import customs clearance.",
                    fontLoader {
                        size = 15.sp
                        family = FONT_FAMILY
                    },
                    foreground = ColorPaint(Color(primaryDarkColor))
                )
            ).apply {
                wrapsWords = true
            }
            this@ImportServicesFragment += listOf(title, subtitle, image, pTitle, p1, pTitle2, p2)
            this@ImportServicesFragment.layout = Layout.simpleLayout {
                val contentMarginTop = 40.0.sp
                title.position = Point((width - title.width) / 2, 0.0)
                subtitle.position = Point((width - subtitle.width) / 2, 14.0.sp + title.bounds.bottom)
                image.bounds = Rectangle(
                    (width - ServicesFragment.CONTENT_WIDTH) / 2,
                    subtitle.bounds.bottom + contentMarginTop,
                    imageWidth,
                    imageHeight
                )
                pTitle.bounds = Rectangle(
                    image.bounds.right + imageSpacing,
                    subtitle.bounds.bottom + contentMarginTop + 20.0.sp,
                    imageWidth,
                    imageHeight
                )
                p1.bounds = Rectangle(
                    pTitle.x, pTitle.bounds.bottom + 20.0.sp, imageWidth, imageHeight
                )
                pTitle2.bounds = Rectangle(
                    image.bounds.right + imageSpacing, p1.bounds.bottom + 30.0.sp, imageWidth, imageHeight
                )
                p2.bounds = Rectangle(
                    p1.x, pTitle2.bounds.bottom + 20.0.sp, imageWidth, imageHeight
                )
            }
        }
    }
}