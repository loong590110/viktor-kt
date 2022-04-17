package me.julius.apps.viktor.core

import io.nacular.doodle.drawing.FontLoader

interface Context {
    val applicationContext: ApplicationContext
    val fontLoader: FontLoader
    val router: Router
}