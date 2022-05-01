package me.julius.apps.viktor.fragments

import io.nacular.doodle.core.plusAssign
import me.julius.apps.viktor.core.AutomaticFragment
import me.julius.apps.viktor.core.PageContext
import me.julius.apps.viktor.layout.LinearLayout

class FooterFragment(context: PageContext) : AutomaticFragment(context) {
    init {
        val featuresFragment = FeaturesFragment(context)
        val contactsFragment = ContactsFragment(context)
        val copyrightFragment = CopyrightFragment(context)
        this@FooterFragment += listOf(featuresFragment, contactsFragment, copyrightFragment)
        layout = LinearLayout()
    }
}