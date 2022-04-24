package me.julius.apps.viktor.core

import io.nacular.doodle.animation.Animator
import io.nacular.doodle.animation.impl.AnimatorImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

object Modules {
    val AnimatorModule: DI.Module = DI.Module(allowSilentOverride = true, name = "AnimatorModule") {
        bindProvider<Animator> { AnimatorImpl(instance(), instance()) }
    }
}