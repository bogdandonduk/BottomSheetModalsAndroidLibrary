package bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple

import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.anatomy.*
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base.BaseBottomSheetModalModel

class SimpleBottomSheetModalModel(
    override var tag: String,
    var appearance: Appearance,

    var title: Text,
    var textContent: MutableMap<String, Text>,

    var positiveButton: Button,
    var negativeButton: Button,

    var contextMenu: ContextMenu,
    var overflowMenu: OverflowMenu,

    var callbacks: Callbacks
) : BaseBottomSheetModalModel(tag) {
    var savedData = PersistableData()
}