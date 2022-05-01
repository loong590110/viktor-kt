package me.julius.apps.viktor.core

import io.nacular.doodle.core.Container
import io.nacular.doodle.core.View
import io.nacular.doodle.geometry.Rectangle

fun automaticContainer(
    width: AutomaticContainer.Width = AutomaticContainer.Width.MATCH_PARENT,
    height: AutomaticContainer.Height = AutomaticContainer.Height.WRAP_CONTENT,
    block: AutomaticContainer.() -> Unit
): AutomaticContainer {
    return AutomaticContainer(width, height).also(block)
}

fun automaticContainer(
    params: AutomaticContainer.LayoutParams, block: AutomaticContainer.() -> Unit
): AutomaticContainer {
    return AutomaticContainer(params).also(block)
}

open class AutomaticContainer(val layoutParams: LayoutParams) : Container() {
    constructor(width: Width = Width.MATCH_PARENT, height: Height = Height.WRAP_CONTENT) : this(
        LayoutParams(
            width,
            height
        )
    )

    init {
        val onBoundsChanged = { _: View, old: Rectangle, new: Rectangle ->
            if (layoutParams.width == Width.MATCH_PARENT && new.width != width) {
                width = new.width
            }
            if (layoutParams.height == Height.MATCH_PARENT && new.height != height) {
                height = new.height
            }
            if (layoutParams.width is Width.WIDTH_PERCENT) {
                val w = new.width * (layoutParams.width as Width.WIDTH_PERCENT).percent
                if (w != width) {
                    width = w
                }
            }
            if (layoutParams.height is Height.HEIGHT_PERCENT) {
                val h = new.height * (layoutParams.height as Height.HEIGHT_PERCENT).percent
                if (h != height) {
                    height = h
                }
            }
        }
        parentChange += { _, old, new ->
            old?.boundsChanged?.minusAssign(onBoundsChanged)
            new?.boundsChanged?.plusAssign(onBoundsChanged)
        }
        parent?.boundsChanged?.plusAssign(onBoundsChanged)
    }

    override fun doLayout() {
        super.doLayout()
        resize()
    }

    private fun resize() {
        when (layoutParams.width) {
            Width.MATCH_PARENT -> {
                if (width != parent!!.width) {
                    width = parent!!.width
                }
            }
            is Width.WIDTH_PERCENT -> {
                val w = parent!!.width * (layoutParams.width as Width.WIDTH_PERCENT).percent
                if (width != w) {
                    width = w
                }
            }
            Width.WRAP_CONTENT -> {
                val w = children.maxOf { it.bounds.right } + insets.right
                if (w != width) {
                    width = w
                }
            }
            else -> Unit
        }
        when (layoutParams.height) {
            Height.MATCH_PARENT -> {
                if (height != parent!!.height) {
                    height = parent!!.height
                }
            }
            is Height.HEIGHT_PERCENT -> {
                val h = parent!!.height * (layoutParams.height as Height.HEIGHT_PERCENT).percent
                if (height != h) {
                    height = h
                }
            }
            Height.WRAP_CONTENT -> {
                val h = children.maxOf { it.bounds.bottom } + insets.bottom
                if (h != height) {
                    height = h
                }
            }
            else -> Unit
        }
        resizeParentIfNeeded()
    }

    private fun resizeParentIfNeeded() {
        if (parent is AutomaticContainer) {
            val ac = parent as AutomaticContainer
            if (ac.layoutParams.width == Width.WRAP_CONTENT && ac.width - ac.insets.right != width) {
                ac.resize()
            } else if (ac.layoutParams.height == Height.WRAP_CONTENT && ac.height - ac.insets.bottom != height) {
                ac.resize()
            }
        }
    }

    open class LayoutParams(var width: Width = Width.FIXED, var height: Height = Height.FIXED)

    sealed class Width {
        object FIXED : Width()
        object MATCH_PARENT : Width()
        object WRAP_CONTENT : Width()
        class WIDTH_PERCENT(var percent: Float) : Width()
    }

    sealed class Height {
        object FIXED : Height()
        object MATCH_PARENT : Height()
        object WRAP_CONTENT : Height()
        class HEIGHT_PERCENT(var percent: Float) : Height()
    }
}
