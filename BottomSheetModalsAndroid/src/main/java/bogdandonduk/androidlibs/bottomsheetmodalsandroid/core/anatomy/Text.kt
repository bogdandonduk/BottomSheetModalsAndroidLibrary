package bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.anatomy

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt

class Text(
    var text: String,
    @ColorInt var textColor: Int,
    var icon: Drawable? = null,
    @ColorInt var iconTintColor: Int? = null
)