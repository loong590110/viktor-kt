package me.julius.apps.viktor

import io.nacular.doodle.controls.buttons.PushButton
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.layout.constant
import io.nacular.doodle.layout.constrain
import kotlinx.coroutines.CoroutineScope
import me.julius.apps.viktor.core.Page
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.fragments.Header

class HomePage(context: PageContext, mainScope: CoroutineScope) : Page(context) {
    init {
        val name = context.arguments["name"]
        val header = Header(mainScope, fontLoader)
        val btnClick = PushButton("Click $name")
        this += listOf(btnClick, header)
        layout = constrain(btnClick, header) { _btnClick, _header ->
            // btn click
            _btnClick.width = constant(156.0)
            _btnClick.height = constant(36.0)
            _btnClick.center = parent.center
            // header
            _header.width = parent.width
            _header.height = constant(150.0)
        }
        var count = 0
        btnClick.fired += {
            it.text = "Clicked $name ${++count}"
            router.path("/detail").navigate()
        }
    }
}