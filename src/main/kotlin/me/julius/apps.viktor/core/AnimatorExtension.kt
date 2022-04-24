package me.julius.apps.viktor.core

import io.nacular.doodle.animation.Animator
import org.kodein.di.instance

val ApplicationContext.animator: Animator get() = modulesProvider.instance()
val Page.animator: Animator get() = applicationContext.animator
val Fragment.animator: Animator get() = applicationContext.animator
fun animator(context: Context): Animator = context.applicationContext.animator