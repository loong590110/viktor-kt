package me.julius.apps.viktor.theme

import io.nacular.doodle.drawing.Color
import io.nacular.doodle.theme.Modules
import io.nacular.doodle.theme.Modules.Companion.bindBehavior
import io.nacular.doodle.theme.basic.BasicTheme
import me.julius.apps.viktor.widgets.AutoSizeLabel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.erasedSet
import org.kodein.di.instance
import org.kodein.di.singleton

typealias VTheme = ViktorTheme

class ViktorTheme(
    configProvider: ConfigProvider, behaviors: Iterable<Modules.BehaviorResolver>
) : BasicTheme(configProvider, behaviors.filter { it.theme == VTheme::class }) {
    companion object {
        val ViktorTheme: DI.Module = basicThemeModule(name = "ViktorTheme") {
            bind<ViktorTheme>() with singleton { ViktorTheme(instance(), Instance(erasedSet())) }
        }

        fun autoSizeLabelBehavior(foregroundColor: Color? = null): DI.Module =
            basicThemeModule(name = "AutoSizeLabelBehavior") {
                bindBehavior<AutoSizeLabel>(VTheme::class) {
                    it.behavior = instance<BasicThemeConfig>().run {
                        AutoSizeLabelBehavior(instance(), instance(), foregroundColor ?: this.foregroundColor).apply {
                            disabledColorMapper = this@run.disabledColorMapper
                        }
                    }
                }
            }
    }

    override val config: BasicThemeConfig = ViktorThemeConfig()

    class ViktorThemeConfig : BasicThemeConfig
}