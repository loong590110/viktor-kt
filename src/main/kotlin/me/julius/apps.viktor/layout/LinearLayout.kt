package me.julius.apps.viktor.layout

import io.nacular.doodle.core.Layout
import io.nacular.doodle.core.Positionable
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.utils.HorizontalAlignment
import io.nacular.doodle.utils.Orientation
import io.nacular.doodle.utils.VerticalAlignment

open class LinearLayout(
    private val orientation: Orientation = Orientation.Vertical,
    private val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Left,
    private val verticalAlignment: VerticalAlignment = VerticalAlignment.Top,
    private val spacing: Double = 0.0
) : Layout {
    override fun layout(container: PositionableContainer) {
        if (container.children.isEmpty()) {
            return
        }
        val availableWidth = container.width - container.insets.left - container.insets.right
        val availableHeight = container.height - container.insets.top - container.insets.bottom
        if (orientation == Orientation.Horizontal) {
            val y = { it: Positionable ->
                when (verticalAlignment) {
                    VerticalAlignment.Top -> container.insets.top
                    VerticalAlignment.Middle -> (availableHeight - it.height) / 2
                    VerticalAlignment.Bottom -> availableHeight - it.height + container.insets.top
                }
            }
            when (horizontalAlignment) {
                HorizontalAlignment.Left -> {
                    var x = container.insets.left + spacing
                    container.children.forEach {
                        it.x = x
                        it.y = y(it)
                        x += it.width + spacing
                    }
                }
                HorizontalAlignment.Center -> {
                    val childrenWidth = container.children.sumOf { it.width }
                    val spacing = (availableWidth - childrenWidth) / (container.children.size + 1)
                    var x = container.insets.left + spacing
                    container.children.forEach {
                        it.x = x
                        it.y = y(it)
                        x += it.width + spacing
                    }
                }
                HorizontalAlignment.Right -> {
                    val childrenWidth = container.children.sumOf { it.width }
                    val leftSpacing = availableWidth - childrenWidth - spacing * (container.children.size - 1)
                    var x = container.insets.left + leftSpacing
                    container.children.forEach {
                        it.x = x
                        it.y = y(it)
                        x += it.width + spacing
                    }
                }
            }
        } else {
            val x = { it: Positionable ->
                when (horizontalAlignment) {
                    HorizontalAlignment.Left -> container.insets.left
                    HorizontalAlignment.Center -> (availableWidth - it.width) / 2
                    HorizontalAlignment.Right -> availableWidth - it.width + container.insets.left
                }
            }
            when (verticalAlignment) {
                VerticalAlignment.Top -> {
                    var y = container.insets.top + spacing
                    container.children.forEach {
                        it.x = x(it)
                        it.y = y
                        y += it.height + spacing
                    }
                }
                VerticalAlignment.Middle -> {
                    val childrenHeight = container.children.sumOf { it.height }
                    val spacing = (availableHeight - childrenHeight) / (container.children.size + 1)
                    var y = container.insets.top + spacing
                    container.children.forEach {
                        it.x = x(it)
                        it.y = y
                        y += it.height + spacing
                    }
                }
                VerticalAlignment.Bottom -> {
                    val childrenHeight = container.children.sumOf { it.height }
                    val topSpacing = availableHeight - childrenHeight - spacing * (container.children.size - 1)
                    var y = container.insets.top + topSpacing
                    container.children.forEach {
                        it.x = x(it)
                        it.y = y
                        y += it.height + spacing
                    }
                }
            }
        }
    }
}