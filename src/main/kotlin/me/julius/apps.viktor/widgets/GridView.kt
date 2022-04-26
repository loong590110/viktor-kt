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
    private val rows = ceil(data.size.toDouble() / spanCount)

    init {
        data.forEachIndexed { index, item ->
            this@GridView += creator(index, item)
        }
    }

    override fun doLayout() {
        width = (itemSize * spanCount.toDouble() + spacing * (spanCount - 1.0)).width + insets.left + insets.right
        height = (itemSize * rows + spacing * (rows - 1.0)).height + insets.top + insets.bottom
        children.forEachIndexed { index, view ->
            val x = index % spanCount * (itemSize.width + spacing.width) + insets.left
            val y = index / spanCount * (itemSize.height + spacing.height) + insets.top
            view.bounds = Rectangle(Point(x, y), itemSize)
        }
    }
}