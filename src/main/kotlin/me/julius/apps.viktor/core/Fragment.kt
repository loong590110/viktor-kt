package me.julius.apps.viktor.core

import io.nacular.doodle.core.Container
import io.nacular.doodle.drawing.Canvas
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.drawing.FontLoader
import io.nacular.doodle.geometry.Rectangle
import kotlinx.coroutines.CoroutineScope

open class Fragment(val context: PageContext) : Container(), Context {
    override val applicationContext: ApplicationContext get() = context.applicationContext
    override val mainScope: CoroutineScope get() = context.mainScope
    override val fontLoader: FontLoader get() = context.fontLoader
    override val router: Router get() = context.router
    override fun render(canvas: Canvas) {
        backgroundColor?.apply {
            canvas.rect(Rectangle(0.0, 0.0, width, height), ColorPaint(this))
        }
    }
}