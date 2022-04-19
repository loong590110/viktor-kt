package me.julius.apps.viktor.theme

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.controls.theme.CommonLabelBehavior
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.FontLoader
import io.nacular.doodle.drawing.TextMetrics
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.text.StyledText
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import me.julius.apps.viktor.widgets.AutoSizeLabel
import kotlin.math.max
import kotlin.math.min

class AutoSizeLabelBehavior(
    private val fontLoader: FontLoader,
    textMetrics: TextMetrics,
    override val foregroundColor: Color? = null,
    override val backgroundColor: Color? = null
) : CommonLabelBehavior(textMetrics, foregroundColor, backgroundColor) {
    private val coroutineScope by lazy { MainScope() }
    private var sizeCache: Size? = null
    private var job: Job? = null

    override fun measureText(label: Label): Size {
        if (label is AutoSizeLabel && label.autoSize) {
            if (sizeCache == label.size) {
                return label.size
            }
            sizeCache = label.size
            if (job?.isActive == true) {
                return label.size
            }
            var labelWidth = sizeCache!!.width
            var labelHeight = sizeCache!!.height
            if (labelWidth == 0.0 || labelHeight == 0.0) {
                return label.size
            }
            job = coroutineScope.launch {
                val data = label.styledText.toList()
                if (data.size > 1) {
                    throw IllegalStateException("Unsupported multiple of style text")
                }
                val (text, style) = data[0]
                var fontSize = style.font?.size ?: label.maxFontSize
                label.maxFontSize = max(fontSize, label.maxFontSize)
                var size = super.measureText(label)
                var overflow = size.height > labelHeight
                while (true) {
                    if (labelWidth != sizeCache!!.width || labelHeight != sizeCache!!.height) {
                        labelWidth = sizeCache!!.width
                        labelHeight = sizeCache!!.height
                        size = super.measureText(label)
                        overflow = size.height > labelHeight
                    }
                    if (overflow) {
                        fontSize -= label.stepGranularity
                    } else {
                        fontSize += label.stepGranularity
                    }
                    fontSize = min(label.maxFontSize, max(fontSize, label.minFontSize))
                    val font = fontLoader {
                        this.size = fontSize
                        style.font?.let {
                            this.family = it.family
                            this.weight = it.weight
                            this.style = it.style
                        }
                    }
                    val styledText = StyledText(
                        text = text,
                        font = font,
                        foreground = style.foreground,
                        background = style.background,
                        decoration = style.decoration
                    )
                    if (overflow) {
                        label.styledText = styledText
                        size = super.measureText(label)
                        if (size.height <= labelHeight || fontSize == label.minFontSize) {
                            break
                        }
                    } else {
                        val tempLabel = Label(styledText).apply {
                            this.verticalAlignment = label.verticalAlignment
                            this.horizontalAlignment = label.horizontalAlignment
                            this.wrapsWords = label.wrapsWords
                            this.fitText = label.fitText
                            this.size = label.size
                        }
                        size = super.measureText(tempLabel)
                        if (size.height > labelHeight || fontSize == label.maxFontSize) {
                            break
                        }
                        label.styledText = styledText
                    }
                }
            }
            return label.size
        }
        return super.measureText(label)
    }
}