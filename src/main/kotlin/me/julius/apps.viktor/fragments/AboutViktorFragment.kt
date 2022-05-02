package me.julius.apps.viktor.fragments

import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.core.Layout
import io.nacular.doodle.core.plusAssign
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.ColorPaint
import io.nacular.doodle.drawing.Font
import io.nacular.doodle.geometry.Point
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.text.StyledText
import me.julius.apps.viktor.FONT_FAMILY
import me.julius.apps.viktor.ViktorColors.primaryDarkColor
import me.julius.apps.viktor.core.AutoSize.sp
import me.julius.apps.viktor.core.AutomaticFragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.widgets.ImageView
import me.julius.apps.viktor.widgets.ScrollView

class AboutViktorFragment(context: PageContext) : AutomaticFragment(context, height = Height.MATCH_PARENT) {
    private var onScroll: ((Double, Double) -> Unit)? = null
    private val scrollView: ScrollView

    init {
        scrollView = ScrollView(context) {
            val banner = ImageView(context, "images/bg_about_viktor.jpg")
            val title = Label(
                StyledText("ABOUT US", fontLoader {
                    size = 24.sp
                    family = FONT_FAMILY
                }, foreground = ColorPaint(Color(primaryDarkColor)))
            )
            val subtitle = Label(
                StyledText(
                    "Provide comprehensive and tailor-made storage solutions and services", fontLoader {
                        size = 14.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color(primaryDarkColor))
                )
            )
            val logisticsSpacing = 40.0.sp
            val logisticsWidth = ServicesFragment.CONTENT_WIDTH / 2.0 - logisticsSpacing / 2.0
            val logisticsHeight = logisticsWidth * 573.0 / 800.0
            val logistics = ImageView(context, "images/logistics.png")
            val pTitle = Label(
                StyledText("Viktor Rack & Warehouse Equipment Manufacturing Co., Ltd", fontLoader {
                    size = 18.sp
                    family = FONT_FAMILY
                    weight = Font.Bold
                }, foreground = ColorPaint(Color(primaryDarkColor)))
            )
            val p1 = Label(
                StyledText(
                    "Establishedin 2000, ", fontLoader {
                        size = 15.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color(primaryDarkColor))
                ) + StyledText(
                    "VIKTOR", fontLoader {
                        size = 16.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color(primaryDarkColor))
                ) + StyledText(
                    " warehouse equipment company was one of the manufacturers of in racking and automated warehouse logistics system with professional services as planning, design, manufacture, installation, debuggingand. After years of development,",
                    fontLoader {
                        size = 15.sp
                        family = FONT_FAMILY
                    },
                    foreground = ColorPaint(Color(primaryDarkColor))
                ) + StyledText(
                    "VIKTOR", fontLoader {
                        size = 16.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color(primaryDarkColor))
                ) + StyledText(
                    " has already become the brand famous supplier of racking system and logistics system integrator.",
                    fontLoader {
                        size = 15.sp
                        family = FONT_FAMILY
                    },
                    foreground = ColorPaint(Color(primaryDarkColor))
                )
            ).apply {
                wrapsWords = true
            }
            val p2 = Label(
                StyledText(
                    "VIKTOR", fontLoader {
                        size = 16.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color(primaryDarkColor))
                ) + StyledText(
                    " strictlycomply with ", fontLoader {
                        size = 15.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color(primaryDarkColor))
                ) + StyledText(
                    "ISO9001，ISO14001", fontLoader {
                        size = 16.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color(primaryDarkColor))
                ) + StyledText(
                    " and ", fontLoader {
                        size = 15.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color(primaryDarkColor))
                ) + StyledText(
                    "ISO10012.", fontLoader {
                        size = 16.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color(primaryDarkColor))
                ) + StyledText(
                    "Following thestrategy to provide customers with products that meet customers' request and expectations,",
                    fontLoader {
                        size = 15.sp
                        family = FONT_FAMILY
                    },
                    foreground = ColorPaint(Color(primaryDarkColor))
                ) + StyledText(
                    "VIKTOR", fontLoader {
                        size = 16.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color(primaryDarkColor))
                ) + StyledText(
                    " will continue to insist on the pursuit of international advanced mode ofmanagement and control to ensure the high quality and stability of the products. ",
                    fontLoader {
                        size = 15.sp
                        family = FONT_FAMILY
                    },
                    foreground = ColorPaint(Color(primaryDarkColor))
                ) + StyledText(
                    "VIKTOR", fontLoader {
                        size = 16.sp
                        family = FONT_FAMILY
                    }, foreground = ColorPaint(Color(primaryDarkColor))
                ) + StyledText(
                    " alsopays a great attention to the development and innovation of products. We built Nanjing municipaltechnical research and development center with more than 20 national patentsand softwarecopyright on racking, office furniture,stacking machine, conveyor, shuttle car,warehouse control and management.",
                    fontLoader {
                        size = 15.sp
                        family = FONT_FAMILY
                    },
                    foreground = ColorPaint(Color(primaryDarkColor))
                ) + StyledText(
                    " VIKTOR has two factories, coveringan area of about 20000 square meters. We have 12 automated production lineswith continuous high precision CNC punching, roll forming, automatic weldingrobot, automatic shot, automatic painting, and goldprocessing and cold CNC universal machine tool. It formed the tomanufacture various types of racking, stacker, conveyor, shuttle car, shuttle,rotating platform, high-speed elevator, shape and position detection, foldingmachine and other hardware devices, we also have a implementation team, covering machinery, electricalcontrol software, etc.",
                    fontLoader {
                        size = 15.sp
                        family = FONT_FAMILY
                    },
                    foreground = ColorPaint(Color(primaryDarkColor))
                ) + StyledText(
                    " Since VIKTOR founded, we already have done tens of thousands of automated storage andretrieval system and racking projects. VIKTOR becomes the designated supplier oflogistic equipment for famous enterprise at home and aboard such as:WALMART、AMAZON、FAW,Volkswagen, Mercedes Benz, Yangzi Petrochemical, BASF, Yili, unified, Yurun,Suning, Jingdong, Dangdang, Baiyun Airport,  FEDEX,PetroChina, Sinopec, iRIS, Xi'an Metroaviation engine.",
                    fontLoader {
                        size = 15.sp
                        family = FONT_FAMILY
                    },
                    foreground = ColorPaint(Color(primaryDarkColor))
                )
            ).apply {
                wrapsWords = true
            }
            val concept = ConceptFragment(context)
            val footer = FooterFragment(context)
            this@ScrollView += listOf(banner, title, subtitle, logistics, pTitle, p1, p2, concept, footer)
            this@ScrollView.layout = Layout.simpleLayout {
                banner.bounds = Rectangle(0.0, HeaderFragment.HEIGHT, width, 300.0.sp)
                title.position = Point((width - title.width) / 2, 50.0.sp + banner.bounds.bottom)
                subtitle.position = Point((width - subtitle.width) / 2, 14.0.sp + title.bounds.bottom)
                logistics.bounds = Rectangle(
                    (width - ServicesFragment.CONTENT_WIDTH) / 2,
                    subtitle.bounds.bottom + 20.0.sp,
                    logisticsWidth,
                    logisticsHeight
                )
                pTitle.bounds = Rectangle(
                    logistics.bounds.right + logisticsSpacing,
                    subtitle.bounds.bottom + 20.0.sp,
                    logisticsWidth,
                    logisticsHeight
                )
                p1.bounds = Rectangle(
                    pTitle.x, pTitle.bounds.bottom + 10.0.sp, logisticsWidth, logisticsHeight
                )
                p2.bounds = Rectangle(
                    p1.x, p1.bounds.bottom + 5.0.sp, logisticsWidth, logisticsHeight
                )
                concept.y = p2.bounds.bottom + 20.0.sp
                footer.y = concept.bounds.bottom + 50.0.sp
            }
        }.apply {
            setOnScrollListener { x, y ->
                onScroll?.invoke(x, y)
            }
        }
        this@AboutViktorFragment += scrollView
        this@AboutViktorFragment.layout = Layout.simpleLayout { scrollView.size = Size(width, height) }
    }

    fun setOnScrollListener(block: (Double, Double) -> Unit) {
        onScroll = block
    }

    fun scrollTop() {
        scrollView.scrollTo(0.0, 0.0)
    }
}