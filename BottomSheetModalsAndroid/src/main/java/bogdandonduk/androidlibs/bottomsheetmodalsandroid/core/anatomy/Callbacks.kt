package bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.anatomy

import android.content.DialogInterface

class Callbacks(
    var onCancelAction: ((dialog: DialogInterface) -> Unit)? = null,
    var onDismissAction: ((dialog: DialogInterface) -> Unit)? = null
)