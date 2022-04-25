package me.julius.apps.viktor.fragments

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.drawing.Canvas
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.drawing.Stroke
import io.nacular.doodle.drawing.opacity
import io.nacular.doodle.event.PointerListener
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.layout.Insets
import io.nacular.doodle.text.StyledText
import io.nacular.doodle.utils.HorizontalAlignment
import io.nacular.doodle.utils.VerticalAlignment
import kotlinx.coroutines.launch
import me.julius.apps.viktor.FONT_FAMILY
import me.julius.apps.viktor.ViktorColors
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.Fragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.core.imageLoader
import me.julius.apps.viktor.core.plus
import me.julius.apps.viktor.layout.LinearLayout
import me.julius.apps.viktor.widgets.ImageView

class FloatMenu(context: PageContext, pointerListener: FloatMenu.(Int, Boolean) -> Unit) : Fragment(context) {
    init {
        mainScope.launch {
            val iconSize = 40.0.sp
            val phtTelephone = ImageView(imageLoader.load("images/telephone.svg")!!)
            val phtQQ = ImageView(imageLoader.load("images/qq.svg")!!)
            val phtScanning = ImageView(imageLoader.load("images/scanning.svg")!!)
            val phtDirectionUp = ImageView(imageLoader.load("images/direction-up.svg")!!)
            this@FloatMenu += listOf(phtTelephone, phtQQ, phtScanning, phtDirectionUp).onEachIndexed { i, it ->
                it.size = Size(iconSize, iconSize)
                it.insets = Insets(5.0.sp)
                val enteredColor = Color(0xffffffu) opacity 0.2f
                val exitedColor = Color(0xffffffu) opacity 0.0f
                val radius = 5.0.sp
                it.pointerChanged += PointerListener.entered { _ ->
                    it.setBackgroundColor(enteredColor, radius)
                    pointerListener(i, true)
                }
                it.pointerChanged += PointerListener.exited { _ ->
                    it.setBackgroundColor(exitedColor, radius)
                    pointerListener(i, false)
                }
            }.also {
                val width = 60.0.sp
                size = Size(width, width * it.size)
                insets = Insets((-10.0).sp, 0.0, (-10.0).sp, 0.0)
            }
            layout = LinearLayout(
                horizontalAlignment = HorizontalAlignment.Center, verticalAlignment = VerticalAlignment.Middle
            )
        }
    }

    override fun render(canvas: Canvas) {
        canvas.rect(Rectangle(0.0, 0.0, width, height), fill = ColorPaint(Color(ViktorColors.primaryColor)))
    }
}

class TelephoneCard(context: PageContext) : FloatCard(context) {
    init {
        mainScope.launch {
            val title = Label(
                StyledText(
                    "Consumer Hotline", fontLoader {
                        size = 16.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color.White)
                )
            ).apply {
                width = contentSize.width
            }
            val content = Label(
                StyledText(
                    "86-25-52213408", fontLoader {
                        size = 20.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color.White)
                )
            ).apply {
                width = contentSize.width
            }
            this@TelephoneCard += listOf(title, content)
            layout = LinearLayout(
                horizontalAlignment = HorizontalAlignment.Center, verticalAlignment = VerticalAlignment.Middle
            )
        }
    }
}

class QQCard(context: PageContext) : FloatCard(context) {
    init {
        mainScope.launch {
            val qq1 = Label(
                StyledText(
                    "QQ 757259599", fontLoader {
                        size = 18.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color.White)
                )
            ).apply {
                width = contentSize.width
            }
            val qq2 = Label(
                StyledText(
                    "QQ 675863519", fontLoader {
                        size = 18.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color.White)
                )
            ).apply {
                width = contentSize.width
            }
            this@QQCard += listOf(qq1, qq2)
            layout = LinearLayout(
                horizontalAlignment = HorizontalAlignment.Center, verticalAlignment = VerticalAlignment.Middle
            )
        }
    }
}

class QRCodeCard(context: PageContext) : FloatCard(context) {
    init {
        contentSize = Size(200.0.sp, 220.0.sp)
        mainScope.launch {
            val title = Label(
                StyledText(
                    "Special offer", fontLoader {
                        size = 16.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color.White)
                )
            ).apply {
                width = contentSize.width
            }
            val content = ImageView(imageLoader.load("images/qrcode.jpg")!!).apply {
                size = Size(contentSize.width * 0.7, contentSize.width * 0.7)
            }
            this@QRCodeCard += listOf(title, content)
            layout = LinearLayout(
                horizontalAlignment = HorizontalAlignment.Center, verticalAlignment = VerticalAlignment.Middle
            )
        }
    }
}

class NavigationCard(context: PageContext) : FloatCard(context) {
    init {
        contentSize = Size(200.0.sp, 40.0.sp)
        mainScope.launch {
            val title = Label(
                StyledText(
                    "Click to go back the top", fontLoader {
                        size = 16.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color.White)
                )
            ).apply {
                width = contentSize.width
            }
            this@NavigationCard += listOf(title)
            layout = LinearLayout(
                horizontalAlignment = HorizontalAlignment.Center, verticalAlignment = VerticalAlignment.Middle
            )
        }
    }
}

open class FloatCard(context: PageContext) : Fragment(context) {
    protected val shadowSize = 8.0.sp
    protected var contentSize = Size(200.0.sp, 100.0.sp)
        set(value) {
            field = value
            size = contentSize + Size(shadowSize * 4, shadowSize * 4)
        }

    init {
        size = contentSize + Size(shadowSize * 4, shadowSize * 4)
        insets = Insets(shadowSize * 3, 0.0, shadowSize * 3, 0.0)
    }

    override fun render(canvas: Canvas) {
        canvas.outerShadow(blurRadius = shadowSize) {
            rect(
                Rectangle(shadowSize * 2, shadowSize * 2, contentSize.width, contentSize.height),
                radius = 10.0.sp,
                stroke = Stroke(color = Color(0x666666u)),
                fill = ColorPaint(Color(0x333333u))
            )
        }
    }
}