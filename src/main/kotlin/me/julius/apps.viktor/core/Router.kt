package me.julius.apps.viktor.core

import io.nacular.doodle.core.minusAssign
import io.nacular.doodle.core.plusAssign
import kotlinx.browser.window

class Router(private val context: ApplicationContext) {

    private val history = LinkedHashMap<String, Page>()
    private var visiblePage: Page? = null

    var routes: ((String) -> (PageContext) -> Page)? = null
        set(value) {
            if (field != null || value == null) {
                return
            }
            field = value
            val refresh = {
                val path = parsePathWithArgs(window.location.href)
                path(path).navigate()
            }
            refresh()
            window.onpopstate = {
                refresh()
                false
            }
        }

    fun path(path: String) = object : Navigator {
        override fun navigate() {
            if (routes == null) {
                return
            }
            val hisPage = history[path]
            if (hisPage != null) {
                visiblePage?.visible = false
                hisPage.visible = true
                visiblePage = hisPage
                return
            }
            val (_path, _args) = splitPathAndArgs(path)
            val pageContext = PageContext(context, path, parseArguments(_args))
            val page = routes!!.invoke(_path)(pageContext)
            context.display += page
            history[path] = page
            visiblePage?.visible = false
            page.visible = true
            visiblePage = page
            window.location.href = "${window.location.pathname}#$path"
        }
    }

    fun close(page: Page) {
        history -= page.context.path
        context.display -= page
        context.display.lastOrNull { it is Page }?.also {
            visiblePage = it as Page
            it.visible = true
            window.location.href = "${window.location.pathname}#${it.context.path}"
        } ?: path("/").navigate()
    }

    private fun parsePathWithArgs(url: String): String {
        val index = url.indexOf('#')
        if (index != -1) {
            val path = url.substring(index + 1)
            if (path.isNotEmpty()) {
                return path
            }
        }
        return "/"
    }

    private fun splitPathAndArgs(url: String): Pair<String, String> {
        var endIndex = url.indexOf('#')
        val path = if (endIndex != -1) url.substring(0, endIndex) else url
        endIndex = path.indexOf('?')
        return if (endIndex != -1) {
            val args = path.substring(endIndex + 1)
            path.substring(0, endIndex) to args
        } else {
            path to ""
        }
    }

    private fun parseArguments(args: String): Map<String, Any> {
        if (args.isEmpty()) {
            return emptyMap()
        }
        return args.split('&').associate {
            val pair = it.split('=')
            if (pair.size != 2) {
                throw IllegalArgumentException("Invalid url arguments format.")
            }
            pair[0] to pair[1]
        }
    }

    interface Navigator {
        fun navigate()
    }
}