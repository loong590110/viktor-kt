package me.julius.apps.viktor.core

import io.nacular.doodle.core.Display
import io.nacular.doodle.drawing.FontLoader
import io.nacular.doodle.theme.ThemeManager
import io.nacular.doodle.theme.adhoc.DynamicTheme
import io.nacular.doodle.utils.SetPool
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.kodein.di.bindings.NoArgBindingDI
import org.kodein.di.instance
import org.w3c.dom.events.WheelEvent

class ApplicationContext(val modulesProvider: NoArgBindingDI<*>) : BaseContext() {
    override val applicationContext: ApplicationContext = this
    val display: Display = modulesProvider.instance()
    val themeManager: ThemeManager = modulesProvider.instance()
    val theme: DynamicTheme = modulesProvider.instance()
    override val fontLoader: FontLoader = modulesProvider.instance()
    override val mainScope: CoroutineScope by lazy { MainScope() }
    override val router: Router by lazy { Router(this) }
    val onWheel by lazy { SetPool<(WheelEvent) -> Unit>() }

    init {
        window.onwheel = { e ->
            onWheel.forEach { it(e) }
        }
    }
}