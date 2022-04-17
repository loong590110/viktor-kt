package me.julius.apps.viktor.core

import io.nacular.doodle.core.Container
import io.nacular.doodle.drawing.FontLoader
import io.nacular.doodle.geometry.Size

open class Page(val context: PageContext) : Container(), Context {
    override val applicationContext: ApplicationContext = context.applicationContext
    override val fontLoader: FontLoader = context.fontLoader
    override val router: Router = context.router
    var fixedSize: Size? = null
        set(value) {
            if (value == null) {
                return
            }
            field = value
            size = value
        }

    init {
        this.size = context.applicationContext.display.size
        context.applicationContext.display.sizeChanged += lambda@{ _, _, new ->
            if (fixedSize != null) {
                return@lambda
            }
            this.size = new
        }
    }
}