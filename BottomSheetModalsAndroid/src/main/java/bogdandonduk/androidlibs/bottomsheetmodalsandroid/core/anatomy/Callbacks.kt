package bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.anatomy

import com.google.android.material.bottomsheet.BottomSheetDialogFragment

@PublishedApi
internal class Callbacks(
    var onCancelAction: ((modal: BottomSheetDialogFragment) -> Unit)? = null,
    var onDismissAction: ((modal: BottomSheetDialogFragment) -> Unit)? = null
)