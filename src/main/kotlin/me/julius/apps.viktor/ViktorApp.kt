package me.julius.apps.viktor

import io.nacular.doodle.application.Application
import me.julius.apps.viktor.core.ApplicationContext
import kotlin.js.Date

class ViktorApp(context: ApplicationContext) : Application {
    init {
        println("Started at ${Date().toLocaleString()}")
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