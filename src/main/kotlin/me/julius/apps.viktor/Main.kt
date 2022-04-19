package me.julius.apps.viktor

import io.nacular.doodle.application.Modules
import io.nacular.doodle.application.application
import io.nacular.doodle.theme.basic.BasicTheme
import me.julius.apps.viktor.core.ApplicationContext
import me.julius.apps.viktor.theme.ViktorTheme
import org.kodein.di.instance

fun main() {
    application(
        modules = listOf(
            Modules.PointerModule, Modules.FontModule, ViktorTheme.ViktorTheme
        ) + BasicTheme.basicThemeBehaviors + ViktorTheme.autoSizeLabelBehavior()
    ) {
        ViktorApp(
            ApplicationContext(
                display = instance(),
                themeManager = instance(),
                theme = instance(),
                fontLoader = instance(),
                textMetrics = instance()
            )
        )
    }
}