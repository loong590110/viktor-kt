package me.julius.apps.viktor.core

import io.nacular.doodle.geometry.Size
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun mainScope(context: Context, block: suspend CoroutineScope.() -> Unit) = context.mainScope.launch { block() }

operator fun Size.plus(other: Size) = Size(width + other.width, height + other.height)
operator fun Size.minus(other: Size) = Size(width - other.width, height - other.height)
operator fun Size.times(number: Double) = Size(width * number, height * number)
operator fun Size.div(number: Double) = Size(width / number, height / number)