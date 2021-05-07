package bogdandonduk.androidlibs.bottomsheetmodalsandroid.anatomy

import android.view.View
import androidx.annotation.ColorInt
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ButtonItem(var text: String, @ColorInt var textColor: Int, var clickAction: (view: View, modal: BottomSheetDialogFragment) -> Unit)
