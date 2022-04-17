package me.julius.apps.viktor

import io.nacular.doodle.application.Modules
import io.nacular.doodle.application.application
import io.nacular.doodle.theme.basic.BasicTheme
import io.nacular.doodle.theme.native.NativeTheme
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import me.julius.apps.viktor.core.ApplicationContext
import org.kodein.di.instance

fun main() {
    application(
        modules = listOf(
            Modules.PointerModule, Modules.FontModule, NativeTheme.NativeTheme
        ) + NativeTheme.nativeThemeBehaviors + BasicTheme.basicLabelBehavior()
    ) {
        ViktorApp(
            ApplicationContext(
                display = instance(), themeManager = instance(), theme = instance(), fontLoader = instance()
            )
        )
    }
}