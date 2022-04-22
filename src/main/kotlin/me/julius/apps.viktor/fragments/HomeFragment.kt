package me.julius.apps.viktor.fragments

import io.nacular.doodle.controls.Photo
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.utils.HorizontalAlignment
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.Fragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.imageLoader
import me.julius.apps.viktor.layout.LinearLayout
import me.julius.apps.viktor.widgets.Banner

class HomeFragment(context: PageContext) : Fragment(context) {

    private var isBannerIdle = true

    init {
        mainScope.launch {
            val photo1 = Photo(imageLoader.load("images/954563.jpg")!!)
            val photo2 = Photo(imageLoader.load("images/1004716.jpg")!!)
            val photo3 = Photo(imageLoader.load("images/1013707.jpg")!!)
            this@HomeFragment += Banner(context, listOf(photo1, photo2, photo3)).apply {
                size = Size(this@HomeFragment.width, 500.0.sp)
                var job: Job? = null
                var timer = {}
                fun play() {
                    if (!isBannerIdle) {
                        return
                    }
                    if (currentItem == 2) currentItem = 0 else currentItem++
                    timer()
                }
                timer = {
                    job = mainScope.launch {
                        delay(5000)
                        play()
                    }
                }
                displayChange += { _, _, displayed ->
                    if (displayed) {
                        timer()
                    } else {
                        job?.cancel()
                    }
                }
                setOnScrollListener {
                    isBannerIdle = it == 1.0
                    if (isBannerIdle) {
                        timer()
                    } else {
                        job?.cancel()
                    }
                }
                timer()
            }
            layout = LinearLayout(horizontalAlignment = HorizontalAlignment.Center)
            backgroundColor = Color.Brown
        }
    }
}