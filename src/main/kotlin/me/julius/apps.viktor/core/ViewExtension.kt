package me.julius.apps.viktor.core

import io.nacular.doodle.animation.Animation
import io.nacular.doodle.animation.Animator
import io.nacular.doodle.animation.NoneUnit
import io.nacular.doodle.animation.noneUnits
import io.nacular.doodle.animation.transition.SpeedUpSlowDown
import io.nacular.doodle.animation.transition.Transition
import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.core.View
import io.nacular.doodle.drawing.Canvas
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.event.PointerEvent
import io.nacular.doodle.event.PointerListener
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.text.StyledText
import io.nacular.measured.units.Measure
import io.nacular.measured.units.Time

var Label.hoverColor: Color
    get() = throw UnsupportedOperationException()
    set(value) {
        val normalStyleText = styledText
        var hoverStyleText: StyledText? = null
        styledText.map { (text, style) ->
            StyledText(
                text, style.font, ColorPaint(value), style.background, style.decoration
            )
        }.forEach {
            hoverStyleText = if (hoverStyleText == null) it else hoverStyleText!!..it
        }
        pointerChanged += object : PointerListener {
            override fun entered(event: PointerEvent) {
                styledText = hoverStyleText!!
            }

            override fun exited(event: PointerEvent) {
                styledText = normalStyleText
            }
        }
    }

fun View.drawBackgroundColor(canvas: Canvas, radius: Double = 0.0) {
    backgroundColor?.apply {
        canvas.rect(Rectangle(0.0, 0.0, width, height), radius = radius, fill = ColorPaint(this))
    }
}

class ViewPropertyAnimator(private val view: View, private val animator: Animator) {
    private var translationX: Double = view.x
    private var translationY: Double = view.y
    private var scaleX: Double = view.width
    private var scaleY: Double = view.height
    private var duration: Double = 200.0

    companion object {
        private val animations = HashMap<View, Animation>()
    }

    fun translationX(x: Double): ViewPropertyAnimator {
        translationX = x
        return this
    }

    fun translationXBy(x: Double): ViewPropertyAnimator {
        translationX += x
        return this
    }

    fun translationY(y: Double): ViewPropertyAnimator {
        translationY = y
        return this
    }

    fun translationYBy(y: Double): ViewPropertyAnimator {
        translationY += y
        return this
    }

    fun scaleX(x: Double): ViewPropertyAnimator {
        scaleX = x
        return this
    }

    fun scaleXBy(x: Double): ViewPropertyAnimator {
        scaleX += x
        return this
    }

    fun scaleY(y: Double): ViewPropertyAnimator {
        scaleY = y
        return this
    }

    fun scaleYBy(y: Double): ViewPropertyAnimator {
        scaleY += y
        return this
    }

    fun duration(duration: Double) {
        this.duration = duration
    }

    fun start(
        block: (Double, Double) -> Transition<NoneUnit> = { _, end ->
            SpeedUpSlowDown(Measure(duration, Time.milliseconds), Measure(end, noneUnits))
        }
    ): Animation? {
        animations[view]?.cancel() // cancel first, any way
        if (translationX != view.x || translationY != view.y || scaleX != view.width || scaleY != view.height) {
            val startX = view.x
            val startY = view.y
            val startW = view.width
            val startH = view.height
            val xOffset = translationX - startX
            val yOffset = translationY - startY
            val wOffset = scaleX - startW
            val hOffset = scaleY - startH
            return animator(0.0 to 1.0).using(block)() {
                var x: Double = startX
                var y: Double = startY
                var w: Double = startW
                var h: Double = startH
                when {
                    xOffset != 0.0 -> x += xOffset * it
                    yOffset != 0.0 -> y += yOffset * it
                    wOffset != 0.0 -> w += wOffset * it
                    hOffset != 0.0 -> h += hOffset * it
                }
                view.bounds = Rectangle(x, y, w, h)
            }.also {
                it.completed += {
                    animations.remove(view)
                }
                animations[view] = it
            }
        }
        return null
    }
}

fun View.animate(context: Context): ViewPropertyAnimator = ViewPropertyAnimator(this, animator(context))
fun Fragment.animate() = animate(applicationContext)