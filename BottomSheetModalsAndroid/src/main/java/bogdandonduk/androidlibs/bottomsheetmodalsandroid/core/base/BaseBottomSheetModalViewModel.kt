package bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base

import androidx.lifecycle.ViewModel
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService

internal abstract class BaseBottomSheetModalViewModel(open var tag: String) : ViewModel() {
    var removeModelFromMapOnModalDismiss: Boolean = true

    override fun onCleared() {
        if(removeModelFromMapOnModalDismiss) BottomSheetModalsService.removeModalModel(tag)

        BottomSheetModalsService.modalCurrentlyShowing = false
    }
}