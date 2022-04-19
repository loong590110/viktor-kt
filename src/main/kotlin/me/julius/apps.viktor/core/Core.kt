package me.julius.apps.viktor.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun mainScope(context: Context, block: CoroutineScope.() -> Unit) = context.mainScope.launch { block() }