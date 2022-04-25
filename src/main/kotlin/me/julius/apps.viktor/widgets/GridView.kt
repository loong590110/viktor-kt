package me.julius.apps.viktor.widgets

import io.nacular.doodle.core.Container
import io.nacular.doodle.core.View
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.geometry.Point
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.geometry.Size
import me.julius.apps.viktor.core.plus
import me.julius.apps.viktor.core.times
import kotlin.math.ceil

class GridView<T>(
    private val spanCount: Int,
    private val itemSize: Size,
    private val spacing: Size,
    data: List<T>,
    creator: (Int, T) -> View
) : Container() {
    init {
        val rows = ceil(data.size.toDouble() / spanCount)
        width = (itemSize * spanCount.toDouble() + spacing * (spanCount - 1.0)).width
        height = (itemSize * rows + spacing * (rows - 1.0)).height
        data.forEachIndexed { index, item ->
            this@GridView += creator(index, item)
        }
    }

    override fun doLayout() {
        children.forEachIndexed { index, view ->
            val x = index % spanCount * (itemSize.width + spacing.width)
            val y = index / spanCount * (itemSize.height + spacing.height)
            view.bounds = Rectangle(Point(x, y), itemSize)
        }
    }
}