package bogdandonduk.androidlibs.bottomsheetmodalsandroid

import androidx.lifecycle.ViewModel

abstract class BaseBottomSheetModalViewModel(open var tag: String) : ViewModel() {
    override fun onCleared() {
        BottomSheetModalsService.removeSimpleModalArgReferenceForTag(tag)
        BottomSheetModalsService.modalShowingCurrently = false
    }
}