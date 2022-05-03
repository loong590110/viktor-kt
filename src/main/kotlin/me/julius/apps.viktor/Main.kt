package me.julius.apps.viktor

import io.nacular.doodle.application.Modules
import io.nacular.doodle.application.application
import io.nacular.doodle.theme.basic.BasicTheme
import me.julius.apps.viktor.core.ApplicationContext
import me.julius.apps.viktor.theme.ViktorTheme

fun main() {
    application(
        modules = listOf(
            Modules.PointerModule,
            Modules.FontModule,
            Modules.ImageModule,
            Modules.UrlViewModule,
            ViktorTheme.ViktorTheme,
            ViktorModules.AnimatorModule
        ) + BasicTheme.basicThemeBehaviors + ViktorTheme.autoSizeLabelBehavior()
    ) {
        ViktorApp(ApplicationContext(this))
    }
}

typealias ViktorModules = me.julius.apps.viktor.core.Modules