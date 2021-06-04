package bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.anatomy

import androidx.annotation.ColorInt

@PublishedApi
internal class Appearance(
    @ColorInt var backgroundColor: Int,

    var strokeWidth: Int,
    @ColorInt var strokeColor: Int,

    @ColorInt var dividerLinesColor: Int,

    var cornerRadiusTopLeftPx: Int,
    var cornerRadiusTopRightPx: Int,
    var cornerRadiusBottomRightPx: Int,
    var cornerRadiusBottomLeftPx: Int
)