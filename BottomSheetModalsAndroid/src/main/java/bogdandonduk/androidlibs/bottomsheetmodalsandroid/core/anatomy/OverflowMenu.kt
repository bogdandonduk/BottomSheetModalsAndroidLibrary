package bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.anatomy

import androidx.annotation.ColorInt
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base.BaseMenu

@PublishedApi
internal class OverflowMenu(
    @ColorInt var overflowIconTintColor: Int? = null,
    override var buttons: MutableMap<String, Button>
) : BaseMenu(buttons)