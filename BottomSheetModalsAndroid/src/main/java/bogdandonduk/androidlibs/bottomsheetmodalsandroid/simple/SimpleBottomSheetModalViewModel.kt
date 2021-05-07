package bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base.BaseBottomSheetModalViewModel

class SimpleBottomSheetModalViewModel(
    var argReference: SimpleBottomSheetModalArgReference,
    override var tag: String
) : BaseBottomSheetModalViewModel(tag) {
    @Suppress("UNCHECKED_CAST")
    class Factory(
        var argReference: SimpleBottomSheetModalArgReference,
        var tag: String
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            SimpleBottomSheetModalViewModel(argReference, tag) as T
    }
}