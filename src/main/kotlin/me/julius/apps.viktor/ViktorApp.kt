package me.julius.apps.viktor

import io.nacular.doodle.application.Application
import me.julius.apps.viktor.core.ApplicationContext
import me.julius.apps.viktor.core.AutoSize
import kotlin.js.Date

class ViktorApp(context: ApplicationContext) : Application {
    init {
        println("Started at ${Date().toLocaleString()}")
        AutoSize.initialize(1707.0)
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