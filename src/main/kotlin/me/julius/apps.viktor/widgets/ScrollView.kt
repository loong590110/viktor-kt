package me.julius.apps.viktor.widgets

import io.nacular.doodle.core.Container
import io.nacular.doodle.core.ContainerBuilder
import io.nacular.doodle.core.container
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.drawing.Canvas
import io.nacular.doodle.event.PointerListener
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.utils.Orientation
import kotlinx.browser.document
import me.julius.apps.viktor.core.drawBackgroundColor
import kotlin.math.max

/**
 * Created by Nicholas Eddy on 2/5/18.
 */

class ScrollView(
    private val orientation: Orientation = Orientation.Vertical, contentBuilder: ContainerBuilder.() -> Unit
) : Container() {
    private val content: Container
    private var onScroll: ((Double, Double) -> Unit)? = null
    var scrollXRange = 0.0; private set
    var scrollYRange = 0.0; private set
    var scrollX = 0.0; private set
    var scrollY = 0.0; private set

    fun setOnScrollListener(block: (Double, Double) -> Unit) {
        onScroll = block
    }

    init {
        this += container(contentBuilder).also { content ->
            this.content = content
            content.children.onEach { child ->
                child.boundsChanged += { _, _, _ ->
                    content.height = content.children.maxOf { it.bounds.bottom } + content.insets.bottom
                    scrollYRange = max(0.0, content.height - this@ScrollView.height)
                }
            }
        }
        pointerChanged += PointerListener.entered {
            document.onwheel = {
                scrollBy(-it.deltaX, -it.deltaY)
            }
        }
    }

    override fun doLayout() {
        if (orientation == Orientation.Vertical) {
            content.bounds = Rectangle(scrollX, scrollY, width, content.height)
        } else {
            throw IllegalStateException("Hasn't implemented")
        }
        onScroll?.invoke(scrollX, scrollY)
    }

    override fun render(canvas: Canvas) {
        drawBackgroundColor(canvas)
    }

    fun scrollTo(x: Double, y: Double) {
        scrollX = x.coerceIn(-scrollXRange, 0.0)
        scrollY = y.coerceIn(-scrollYRange, 0.0)
        relayout()
        rerender()
    }

    fun scrollBy(x: Double, y: Double) {
        scrollTo(scrollX + x, scrollY + y)
    }
}