package me.julius.apps.viktor.core

import io.nacular.doodle.core.Container
import io.nacular.doodle.drawing.Canvas
import io.nacular.doodle.drawing.FontLoader
import io.nacular.doodle.geometry.Size
import kotlinx.coroutines.CoroutineScope

open class Page(private val context: PageContext) : Container(), Context {
    override val applicationContext: ApplicationContext get() = context.applicationContext
    override val mainScope: CoroutineScope get() = context.mainScope
    override val fontLoader: FontLoader get() = context.fontLoader
    override val router: Router get() = context.router
    val path = context.path
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

    override fun render(canvas: Canvas) {
        drawBackgroundColor(canvas)
    }
}