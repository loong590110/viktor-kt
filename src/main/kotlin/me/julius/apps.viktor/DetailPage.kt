package me.julius.apps.viktor

import io.nacular.doodle.controls.buttons.PushButton
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.geometry.Point
import io.nacular.doodle.layout.constant
import io.nacular.doodle.layout.constrain
import me.julius.apps.viktor.core.Page
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.fragments.HeaderFragment

class DetailPage(context: PageContext) : Page(context) {
    init {
        val header = HeaderFragment(context)
        val btnClick = PushButton("close detail page").apply {
            position = Point(150, 150)
        }
        this += listOf(btnClick, header)
        layout = constrain(btnClick, header) { _btnClick, _header ->
            // btn click
            _btnClick.width = constant(186.0)
            _btnClick.height = constant(36.0)
            // header
            _header.width = parent.width
            _header.height = constant(150.0)
        }
        btnClick.fired += {
            router.close(this)
        }
    }
}