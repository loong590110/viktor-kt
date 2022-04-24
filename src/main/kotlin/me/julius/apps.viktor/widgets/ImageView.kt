package me.julius.apps.viktor.widgets

import io.nacular.doodle.accessibility.ImageRole
import io.nacular.doodle.core.View
import io.nacular.doodle.drawing.Canvas
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.image.Image
import io.nacular.doodle.image.height
import io.nacular.doodle.image.width
import io.nacular.doodle.layout.Insets
import me.julius.apps.viktor.core.drawBackgroundColor

/**
 * @see io.nacular.doodle.controls.Photo
 */
open class ImageView(
    private var image: Image, private var radius: Double = 0.0
) : View(accessibilityRole = ImageRole()) {
    private var backgroundRadius: Double = 0.0

    init {
        size = image.size
    }

    public override var insets: Insets
        get() = super.insets
        set(value) {
            super.insets = value
        }

    var scaleType: ScaleType = ScaleType.FIT_XY
        set(value) {
            field = value
            rerender()
        }

    fun setImage(image: Image, radius: Double = this.radius) {
        this.image = image
        this.radius = radius
        rerender()
    }

    fun setScaleType(scaleType: ScaleType) {
        this.scaleType = scaleType
        rerender()
    }

    fun setBackgroundColor(color: Color, radius: Double = backgroundRadius) {
        backgroundColor = color
        backgroundRadius = radius
        rerender()
    }

    override fun render(canvas: Canvas) {
        drawBackgroundColor(canvas, backgroundRadius)
        val insetWidth = insets.left + insets.right
        val insetHeight = insets.top + insets.bottom
        val destination = Rectangle(insets.left, insets.top, width - insetWidth, height - insetHeight)
        val source = getSourceRect(destination)
        canvas.image(image, destination = destination, radius = radius, opacity = opacity, source = source)
    }

    private fun getSourceRect(destRect: Rectangle) = when (scaleType) {
        ScaleType.FIT_XY -> {
            Rectangle(0.0, 0.0, image.width, image.height)
        }
        ScaleType.FIT_CENTER -> {
            Rectangle(0.0, 0.0, image.width, image.height) // todo: to be impl
        }
        ScaleType.CENTER_CROP -> {
            Rectangle(0.0, 0.0, image.width, image.height) // todo: to be impl
        }
    }

    enum class ScaleType {
        FIT_XY, FIT_CENTER, CENTER_CROP
    }
}