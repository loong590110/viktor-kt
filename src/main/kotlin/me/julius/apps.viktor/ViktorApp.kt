package me.julius.apps.viktor

import io.nacular.doodle.application.Application
import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.event.PointerEvent
import io.nacular.doodle.event.PointerListener
import io.nacular.doodle.text.StyledText
import me.julius.apps.viktor.core.ApplicationContext
import me.julius.apps.viktor.core.AutoSize
import kotlin.js.Date

class ViktorApp(context: ApplicationContext) : Application {
    init {
        println("Started at ${Date().toLocaleString()}")
        AutoSize.initialize(1536.0)
        context.themeManager.selected = context.theme
        context.router.routes = {
            when (it) {
                "/" -> { context -> HomePage(context) }
                "/detail" -> { context -> DetailPage(context) }
                else -> { context -> NotFoundPage(context) }
            }
        }
    }

    override fun shutdown() {
        println("Shutdown at ${Date().toLocaleString()}")
    }
}

const val FONT_FAMILY = "Arial, Helvetica, sans-serif"

object ViktorColors {
    val primaryColor = 0xe79434u
    val primaryDarkColor = 0x333333u
    val secondDarkColor = 0x666666u
}

var Label.hoverColor: Color
    get() = throw UnsupportedOperationException()
    set(value) {
        val normalStyleText = styledText
        var hoverStyleText: StyledText? = null
        styledText.map { (text, style) ->
            StyledText(
                text, style.font, ColorPaint(value), style.background, style.decoration
            )
        }.forEach {
            hoverStyleText = if (hoverStyleText == null) it else hoverStyleText!!..it
        }
        pointerChanged += object : PointerListener {
            override fun entered(event: PointerEvent) {
                styledText = hoverStyleText!!
            }

            override fun exited(event: PointerEvent) {
                styledText = normalStyleText
            }
        }
    }