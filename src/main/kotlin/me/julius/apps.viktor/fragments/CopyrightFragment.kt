package me.julius.apps.viktor.fragments

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.text.StyledText
import io.nacular.doodle.utils.HorizontalAlignment
import io.nacular.doodle.utils.VerticalAlignment
import me.julius.apps.viktor.FONT_FAMILY
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.AutomaticFragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.core.mainScope
import me.julius.apps.viktor.layout.LinearLayout

class CopyrightFragment(context: PageContext) : AutomaticFragment(context, Width.MATCH_PARENT, Height.FIXED) {
    init {
        height = 80.0.sp
        mainScope(context) {
            backgroundColor = Color(0x242424u)
            val title = Label(
                StyledText(
                    "All Rights Reserved:All Rights Reserved Website registration / License:Zhejiang ICP NO.18050441",
                    fontLoader {
                        size = 16.sp
                        family = FONT_FAMILY
                    },
                    foreground = ColorPaint(Color.White)
                )
            )
            this@CopyrightFragment += title
            layout = LinearLayout(
                horizontalAlignment = HorizontalAlignment.Center, verticalAlignment = VerticalAlignment.Middle
            )
        }
    }
}