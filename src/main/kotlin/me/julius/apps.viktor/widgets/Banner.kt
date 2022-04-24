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
import me.julius.apps.viktor.core.Timer
import me.julius.apps.viktor.core.drawBackgroundColor
import kotlin.math.abs

class Banner(
    private val context: Context,
    children: List<View>,
    private val orientation: Orientation = Orientation.Horizontal,
    private val transition: (Double, Double) -> Transition<NoneUnit> = { _, end ->
        SpeedUpSlowDown(Measure(500.0, Time.milliseconds), Measure(end, noneUnits))
    },
    var enabledAnimation: Boolean = true
) : Container() {
    private val childrenHolder = children
    private var currentPage: View? = null
    private var removingPage: View? = null
    private var pendingItem: Int = -1
    private var onScroll: ((Double) -> Unit)? = null
    private var isInLayout: Boolean = false
    private var isForward: Boolean = false
    private var isBusy: Boolean = false
    private var timer: Timer? = null

    var currentItem: Int = 0
        set(value) {
            if (field == value) {
                return
            }
            if (isBusy) {
                pendingItem = value
                return
            }
            isBusy = true
            isForward = value > field
            timer?.cancel()
            val remainder = abs(value) % childrenHolder.size
            field = if (value >= 0) remainder else childrenHolder.size - remainder
            addOrReplaceChild()
        }

    init {
        boundsChanged += { _, _, _ ->
            relayout()
            rerender()
        }
        displayChange += { _, _, displayed ->
            if (displayed) {
                timer?.start()
            } else {
                timer?.cancel()
            }
        }
        addOrReplaceChild()
    }

    fun setOnScrollListener(block: (Double) -> Unit) {
        this.onScroll = block
    }

    fun startAutoPlay(interval: Int) {
        timer?.cancel()
        timer = Timer(interval) {
            currentItem++
        }
        if (displayed) {
            timer?.start()
        }
    }

    fun cancelAutoPlay() {
        timer?.cancel()
        timer = null
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
        if (isInLayout) {
            return
        }
        isInLayout = true
        if (enabledAnimation && removingPage != null) {
            removingPage!!.bounds = Rectangle(0.0, 0.0, width, height)
            startAnimation()
        } else {
            currentPage!!.bounds = Rectangle(0.0, 0.0, width, height)
            reset()
        }
    }

    private fun startAnimation() {
        when (isForward) {
            true -> {
                if (orientation == Orientation.Horizontal) {
                    currentPage!!.bounds = Rectangle(width, 0.0, width, height)
                    animator(context)(0.0 to width).using { start, end ->
                        transition(start, end)
                    }.invoke {
                        removingPage!!.x = -it
                        currentPage!!.x = width - it
                        onScroll?.invoke(it / width)
                    }.completed += {
                        onAnimationEnded()
                    }
                } else {
                    currentPage!!.bounds = Rectangle(0.0, height, width, height)
                    animator(context)(0.0 to height).using { start, end ->
                        transition(start, end)
                    }.invoke {
                        removingPage!!.y = -it
                        currentPage!!.y = height - it
                        onScroll?.invoke(it / height)
                    }.completed += {
                        onAnimationEnded()
                    }
                }
            }
            false -> {
                if (orientation == Orientation.Horizontal) {
                    currentPage!!.bounds = Rectangle(-width, 0.0, width, height)
                    animator(context)(0.0 to width).using { start, end ->
                        transition(start, end)
                    }.invoke {
                        removingPage!!.x = it
                        currentPage!!.x = -width + it
                        onScroll?.invoke(it / width)
                    }.completed += {
                        onAnimationEnded()
                    }
                } else {
                    currentPage!!.bounds = Rectangle(0.0, -height, width, height)
                    animator(context)(0.0 to height).using { start, end ->
                        transition(start, end)
                    }.invoke {
                        removingPage!!.y = -it
                        currentPage!!.y = -height + it
                        onScroll?.invoke(it / height)
                    }.completed += {
                        onAnimationEnded()
                    }
                }
            }
        }
    }

    private fun onAnimationEnded() {
        this -= removingPage!!
        reset()
    }

    private fun reset() {
        removingPage = null
        isInLayout = false
        isBusy = false
        if (pendingItem == -1) {
            timer?.start()
        } else {
            currentItem = pendingItem
            pendingItem = -1
        }
    }
}