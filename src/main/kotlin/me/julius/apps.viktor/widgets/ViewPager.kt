package me.julius.apps.viktor.widgets

import io.nacular.doodle.animation.Animation
import io.nacular.doodle.core.Container
import io.nacular.doodle.core.View
import io.nacular.doodle.core.minusAssign
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.drawing.Canvas
import io.nacular.doodle.geometry.Rectangle
import me.julius.apps.viktor.core.drawBackgroundColor

class ViewPager(
    children: List<View>, var enabledAnimation: Boolean = true
) : Container() {
    private val childrenHolder = children
    private var currentPage: View? = null

    var currentItem: Int = 0
        set(value) {
            field = value
            addOrReplaceChild()
        }

    init {
        addOrReplaceChild()
    }

    private fun addOrReplaceChild() {
        if (childrenHolder.isEmpty()) {
            return
        }
        val child = childrenHolder[currentItem]
        if (child == currentPage) {
            return
        }
        currentPage?.let { this -= it }.also { currentPage = child }
        this += child
    }

    override fun render(canvas: Canvas) {
        drawBackgroundColor(canvas)
    }

    override fun doLayout() {
        currentPage!!.bounds = Rectangle(0.0, 0.0, width, height)
    }
}