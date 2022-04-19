package me.julius.apps.viktor.core

import io.nacular.doodle.core.Display
import io.nacular.doodle.drawing.FontLoader
import io.nacular.doodle.theme.ThemeManager
import io.nacular.doodle.theme.adhoc.DynamicTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class ApplicationContext(
    val display: Display,
    val themeManager: ThemeManager,
    val theme: DynamicTheme,
    override val fontLoader: FontLoader
) : BaseContext() {
    override val applicationContext: ApplicationContext = this
    override val mainScope: CoroutineScope by lazy { MainScope() }
    override val router: Router by lazy { Router(this) }
}