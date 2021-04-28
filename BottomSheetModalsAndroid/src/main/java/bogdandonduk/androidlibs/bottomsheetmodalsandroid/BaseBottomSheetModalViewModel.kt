package bogdandonduk.androidlibs.bottomsheetmodalsandroid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService

abstract class BaseBottomSheetModalViewModel(open var tag: String) : ViewModel() {
    override fun onCleared() {
        BottomSheetModalsService.removeSimpleModalArgReferenceForTag(tag)
        BottomSheetModalsService.modalShowingCurrently = false
    }
}