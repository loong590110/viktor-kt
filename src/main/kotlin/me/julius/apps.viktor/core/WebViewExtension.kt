package me.julius.apps.viktor.core

import io.nacular.doodle.UrlView
import io.nacular.doodle.core.Layout
import io.nacular.doodle.core.View
import io.nacular.doodle.core.container
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.geometry.Size
import kotlinx.browser.document
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.instance
import org.w3c.dom.HTMLIFrameElement
import org.w3c.dom.get

fun ApplicationContext.webview(url: String, index: Int = 0): View = modulesProvider.instance<UrlView>().let { urlView ->
    container {
        // FIXME: UrlView 控件 BUG，需要在<iframe>标签渲染后去设置它的 SRC
        var rendered = false
        render = lambda@{
            if (rendered) {
                return@lambda
            }
            rendered = true
            mainScope.launch {
                // 这里必须是异步的
                val iframes = document.getElementsByTagName("iframe")
                (iframes[index] as? HTMLIFrameElement)?.src = url
            }
        }
        this += urlView
        layout = Layout.simpleLayout {
            urlView.size = Size(width, height)
        }
    }
}

fun Page.webview(url: String, index: Int = 0): View = applicationContext.webview(url, index)
fun Fragment.webview(url: String, index: Int = 0): View = applicationContext.webview(url, index)
fun AutomaticFragment.webview(url: String, index: Int = 0): View = applicationContext.webview(url, index)