package me.julius.apps.viktor.widgets

import io.nacular.doodle.animation.NoneUnit
import io.nacular.doodle.animation.noneUnits
import io.nacular.doodle.animation.transition.SpeedUpSlowDown
import io.nacular.doodle.animation.transition.Transition
import io.nacular.doodle.core.Container
import io.nacular.doodle.core.View
import io.nacular.doodle.core.minusAssign
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.drawing.Canvas
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.utils.Orientation
import io.nacular.measured.units.Measure
import io.nacular.measured.units.Time
import me.julius.apps.viktor.animator
import me.julius.apps.viktor.core.Context
import me.julius.apps.viktor.core.drawBackgroundColor

class ViewPager(
    private val context: Context,
    children: List<View>,
    private val orientation: Orientation = Orientation.Horizontal,
    private val transition: (Double, Double) -> Transition<NoneUnit> = { _, end ->
        SpeedUpSlowDown(Measure(200.0, Time.milliseconds), Measure(end, noneUnits))
    },
    var enabledAnimation: Boolean = true
) : Container() {
    private val childrenHolder = children
    private var currentPage: View? = null
    private var removingPage: View? = null
    private var removingItem: Int = -1

    var currentItem: Int = 0
        set(value) {
            removingItem = field
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
        removingPage = currentPage
        currentPage = child
        this += child
        if (!enabledAnimation && removingPage != null) {
            this -= removingPage!!
        }
    }

    override fun render(canvas: Canvas) {
        drawBackgroundColor(canvas)
    }

    override fun doLayout() {
        if (enabledAnimation && removingPage != null) {
            if (removingItem == -1) {
                return
            }
            removingPage!!.bounds = Rectangle(0.0, 0.0, width, height)
            when {
                removingItem < currentItem -> {
                    if (orientation == Orientation.Horizontal) {
                        currentPage!!.bounds = Rectangle(width, 0.0, width, height)
                        animator(context)(0.0 to width).using { start, end ->
                            transition(start, end)
                        }.invoke {
                            removingPage!!.x = -it
                            currentPage!!.x = width - it
                        }.completed += {
                            this@ViewPager -= removingPage!!
                        }
                    } else {
                        currentPage!!.bounds = Rectangle(0.0, height, width, height)
                        animator(context)(0.0 to height).using { start, end ->
                            transition(start, end)
                        }.invoke {
                            removingPage!!.y = -it
                            currentPage!!.y = height - it
                        }.completed += {
                            this@ViewPager -= removingPage!!
                        }
                    }
                }
                removingItem > currentItem -> {
                    if (orientation == Orientation.Horizontal) {
                        currentPage!!.bounds = Rectangle(-width, 0.0, width, height)
                        animator(context)(0.0 to width).using { start, end ->
                            transition(start, end)
                        }.invoke {
                            removingPage!!.x = it
                            currentPage!!.x = -width + it
                        }.completed += {
                            this@ViewPager -= removingPage!!
                        }
                    } else {
                        currentPage!!.bounds = Rectangle(0.0, -height, width, height)
                        animator(context)(0.0 to height).using { start, end ->
                            transition(start, end)
                        }.invoke {
                            removingPage!!.y = -it
                            currentPage!!.y = -height + it
                        }.completed += {
                            this@ViewPager -= removingPage!!
                        }
                    }
                }
            }
            removingItem = -1
        } else {
            currentPage!!.bounds = Rectangle(0.0, 0.0, width, height)
        }
    }
}