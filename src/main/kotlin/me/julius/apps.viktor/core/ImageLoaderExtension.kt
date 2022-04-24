package me.julius.apps.viktor.core

import io.nacular.doodle.image.ImageLoader
import org.kodein.di.instance

val ApplicationContext.imageLoader: ImageLoader get() = modulesProvider.instance()
val Page.imageLoader: ImageLoader get() = applicationContext.imageLoader
val Fragment.imageLoader: ImageLoader get() = applicationContext.imageLoader