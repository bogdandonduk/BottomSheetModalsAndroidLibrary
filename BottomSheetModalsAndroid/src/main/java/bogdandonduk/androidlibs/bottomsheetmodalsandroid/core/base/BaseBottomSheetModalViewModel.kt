package bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base

import androidx.lifecycle.ViewModel
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService

abstract class BaseBottomSheetModalViewModel(open var tag: String) : ViewModel() {

    override fun onCleared() {
        BottomSheetModalsService.removeModalArgReferenceForTag(tag)
        BottomSheetModalsService.removeModalForTag(tag)

        BottomSheetModalsService.modalShowingCurrently = false
    }
}