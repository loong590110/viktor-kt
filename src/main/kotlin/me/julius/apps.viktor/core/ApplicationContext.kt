package me.julius.apps.viktor.core

import io.nacular.doodle.core.Display
import io.nacular.doodle.drawing.FontLoader
import io.nacular.doodle.theme.ThemeManager
import io.nacular.doodle.theme.adhoc.DynamicTheme

class ApplicationContext(
    val display: Display, val themeManager: ThemeManager, val theme: DynamicTheme, override val fontLoader: FontLoader
) : BaseContext() {
    override val applicationContext: ApplicationContext = this
    override val router: Router = Router(this)
}