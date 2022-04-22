package me.julius.apps.viktor.core

import io.nacular.doodle.core.Display
import io.nacular.doodle.drawing.FontLoader
import io.nacular.doodle.theme.ThemeManager
import io.nacular.doodle.theme.adhoc.DynamicTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.kodein.di.bindings.NoArgBindingDI
import org.kodein.di.instance

class ApplicationContext(val noArgBindingDI: NoArgBindingDI<*>) : BaseContext() {
    override val applicationContext: ApplicationContext = this
    val display: Display = noArgBindingDI.instance()
    val themeManager: ThemeManager = noArgBindingDI.instance()
    val theme: DynamicTheme = noArgBindingDI.instance()
    override val fontLoader: FontLoader = noArgBindingDI.instance()
    override val mainScope: CoroutineScope by lazy { MainScope() }
    override val router: Router by lazy { Router(this) }
}