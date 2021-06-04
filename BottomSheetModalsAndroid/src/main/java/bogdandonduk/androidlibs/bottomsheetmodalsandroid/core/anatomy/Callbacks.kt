package bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.anatomy

import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

@PublishedApi
internal class Callbacks(
    var onCancelAction: ((view: View, modal: BottomSheetDialogFragment) -> Unit)? = null,
    var onDismissAction: ((view: View, modal: BottomSheetDialogFragment) -> Unit)? = null
)