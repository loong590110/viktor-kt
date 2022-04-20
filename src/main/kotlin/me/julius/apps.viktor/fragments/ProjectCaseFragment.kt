package me.julius.apps.viktor.fragments

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.utils.HorizontalAlignment
import io.nacular.doodle.utils.VerticalAlignment
import me.julius.apps.viktor.core.Fragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.layout.LinearLayout

class ProjectCaseFragment(context: PageContext) : Fragment(context) {
    init {
        this += Label("PROJECT CASE")
        layout = LinearLayout(
            horizontalAlignment = HorizontalAlignment.Center, verticalAlignment = VerticalAlignment.Middle
        )
    }
}