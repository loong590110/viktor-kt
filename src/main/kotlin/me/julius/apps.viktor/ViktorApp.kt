package me.julius.apps.viktor

import io.nacular.doodle.application.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import me.julius.apps.viktor.core.ApplicationContext
import kotlin.js.Date

class ViktorApp(context: ApplicationContext) : Application {
    init {
        println("Started at ${Date().toLocaleString()}")
        context.themeManager.selected = context.theme
        val mainScope: CoroutineScope = MainScope()
        context.router.routes = {
            when (it) {
                "/" -> { context -> HomePage(context, mainScope) }
                "/detail" -> { context -> DetailPage(context, mainScope) }
                else -> { context -> NotFoundPage(context, mainScope) }
            }
        }
    }

    override fun shutdown() {
        println("Shutdown at ${Date().toLocaleString()}")
    }
}