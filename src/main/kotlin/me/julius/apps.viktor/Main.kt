package me.julius.apps.viktor

import io.nacular.doodle.animation.Animator
import io.nacular.doodle.application.Modules
import io.nacular.doodle.application.application
import io.nacular.doodle.theme.basic.BasicTheme
import me.julius.apps.viktor.core.ApplicationContext
import me.julius.apps.viktor.core.Context
import me.julius.apps.viktor.core.Fragment
import me.julius.apps.viktor.core.Page
import me.julius.apps.viktor.theme.ViktorTheme
import org.kodein.di.instance

fun main() {
    application(
        modules = listOf(
            Modules.PointerModule, Modules.FontModule, ViktorTheme.ViktorTheme, ViktorModules.AnimatorModule
        ) + BasicTheme.basicThemeBehaviors + ViktorTheme.autoSizeLabelBehavior()
    ) {
        ViktorApp(ApplicationContext(this))
    }
}

val ApplicationContext.animator: Animator get() = noArgBindingDI.instance()
val Page.animator: Animator get() = applicationContext.animator
val Fragment.animator: Animator get() = applicationContext.animator

fun animator(context: Context): Animator = context.applicationContext.animator
typealias ViktorModules = me.julius.apps.viktor.Modules