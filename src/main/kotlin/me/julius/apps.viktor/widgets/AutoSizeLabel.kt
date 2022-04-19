package me.julius.apps.viktor.widgets

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.text.StyledText
import io.nacular.doodle.utils.HorizontalAlignment
import io.nacular.doodle.utils.VerticalAlignment

class AutoSizeLabel(
    styledText: StyledText,
    var autoSize: Boolean = true,
    var maxFontSize: Int = 12,
    var minFontSize: Int = 1,
    var stepGranularity: Int = 1,
    verticalAlignment: VerticalAlignment = VerticalAlignment.Middle,
    horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Center
) : Label(styledText, verticalAlignment, horizontalAlignment) {
    constructor(
        text: String,
        autoSize: Boolean = true,
        maxFontSize: Int = 12,
        minFontSize: Int = 1,
        stepGranularity: Int = 1,
        verticalAlignment: VerticalAlignment = VerticalAlignment.Middle,
        horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Center
    ) : this(
        StyledText(text), autoSize, maxFontSize, minFontSize, stepGranularity, verticalAlignment, horizontalAlignment
    )
}