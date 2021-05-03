package bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple

import android.content.DialogInterface
import android.graphics.Color
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SimpleBottomSheetModalArgReference(
    var backgroundColor: Int = Color.WHITE,
    var title: String = "Modal Title",
    var text: String = "Modal Text",
    var textColor: Int = Color.BLACK,
    var titleColor: Int = textColor,
    var positiveButtonText: String = "Okay",
    var positiveButtonTextColor: Int = Color.BLACK,
    var positiveButtonClickAction: (view: View, modal: BottomSheetDialogFragment) -> Unit = { _: View, _: BottomSheetDialogFragment ->

    },
    var negativeButtonText: String = "Cancel",
    var negativeButtonTextColor: Int = positiveButtonTextColor,
    var negativeButtonClickAction: (view: View, modal: BottomSheetDialogFragment) -> Unit = { _: View, _: BottomSheetDialogFragment ->

    },
    var onCancelAction: ((modal: DialogInterface) -> Unit)? = null,
    var onDismissAction: ((modal: DialogInterface) -> Unit)? = null
)