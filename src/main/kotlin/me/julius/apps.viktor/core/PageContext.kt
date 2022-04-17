package me.julius.apps.viktor.core

import io.nacular.doodle.drawing.FontLoader

class PageContext(
    override val applicationContext: ApplicationContext, val path: String, val arguments: Map<String, Any>
) : BaseContext() {
    override val fontLoader: FontLoader = applicationContext.fontLoader
    override val router: Router = applicationContext.router
}