package bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.anatomy

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt

data class Text(
    var text: String,
    @ColorInt var color: Int,
    var icon: Drawable? = null,
    @ColorInt var iconTintColor: Int? = null
)