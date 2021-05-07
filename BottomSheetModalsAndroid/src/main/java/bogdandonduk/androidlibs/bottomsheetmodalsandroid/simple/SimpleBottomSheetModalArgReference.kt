package bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple

import android.content.DialogInterface
import android.graphics.Color
import android.view.View
import androidx.annotation.ColorInt
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.anatomy.AdditionalButtonsSection
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.anatomy.ButtonItem
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.anatomy.TextItem
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base.BaseBottomSheetModalArgReference
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SimpleBottomSheetModalArgReference(
    @ColorInt var backgroundColor: Int = Color.WHITE,
    var title: TextItem = TextItem(null, "Title", Color.BLACK),
    var textItems: MutableList<TextItem> = mutableListOf(),
    var positiveButton: ButtonItem = ButtonItem("Confirm", Color.BLACK) { _: View, _: BottomSheetDialogFragment ->

    },
    var negativeButton: ButtonItem = ButtonItem("Cancel", Color.BLACK) { _: View, _: BottomSheetDialogFragment ->

    },
    var additionalButtonsSection: AdditionalButtonsSection = AdditionalButtonsSection(null, "More options", mutableListOf()),
    var onCancelAction: ((modal: DialogInterface) -> Unit)? = null,
    var onDismissAction: ((modal: DialogInterface) -> Unit)? = null
) : BaseBottomSheetModalArgReference()