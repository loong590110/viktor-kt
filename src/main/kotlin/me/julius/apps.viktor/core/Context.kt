package me.julius.apps.viktor.core

import io.nacular.doodle.drawing.FontLoader
import kotlinx.coroutines.CoroutineScope

interface Context {
    val applicationContext: ApplicationContext
    val mainScope: CoroutineScope
    val fontLoader: FontLoader
    val router: Router
}