package bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple

import android.content.DialogInterface
import android.graphics.Color
import android.view.View
import androidx.annotation.ColorInt
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.anatomy.BottomSheetModalAnatomy
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base.BaseBottomSheetModalArgReference
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SimpleBottomSheetModalArgReference : BaseBottomSheetModalArgReference() {
    @ColorInt var backgroundColor: Int = Color.WHITE

    var title: BottomSheetModalAnatomy.TextItem = BottomSheetModalAnatomy.TextItem(
        text = "Title",
        textColor = Color.BLACK
    )
    var textItems: MutableList<BottomSheetModalAnatomy.TextItem> = mutableListOf()

    var positiveButton: BottomSheetModalAnatomy.ButtonItem = BottomSheetModalAnatomy.ButtonItem("Confirm", Color.BLACK) { _: View, _: BottomSheetDialogFragment ->

    }
    var negativeButton: BottomSheetModalAnatomy.ButtonItem = BottomSheetModalAnatomy.ButtonItem("Cancel", positiveButton.textColor) { _: View, _: BottomSheetDialogFragment ->

    }

    var modalContextPopupMenu: BottomSheetModalAnatomy.Popup.ModalContext.Menu = BottomSheetModalAnatomy.Popup.ModalContext.getMenuBuilder().build()
    var overflowButtonsContextPopupMenu: BottomSheetModalAnatomy.Popup.OverflowButtonsContext.Menu = BottomSheetModalAnatomy.Popup.OverflowButtonsContext.getMenuBuilder().build()

    var onCancelAction: ((modal: DialogInterface) -> Unit)? = null
    var onDismissAction: ((modal: DialogInterface) -> Unit)? = null
}