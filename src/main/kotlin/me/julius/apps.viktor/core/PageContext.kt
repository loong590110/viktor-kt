package me.julius.apps.viktor.core

import io.nacular.doodle.drawing.FontLoader
import kotlinx.coroutines.CoroutineScope

class PageContext(
    override val applicationContext: ApplicationContext, val path: String, val arguments: Map<String, Any>
) : BaseContext() {
    override val mainScope: CoroutineScope get() = applicationContext.mainScope
    override val fontLoader: FontLoader get() = applicationContext.fontLoader
    override val router: Router get() = applicationContext.router
}