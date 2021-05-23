package bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base

import android.util.Log
import androidx.lifecycle.ViewModel
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService

abstract class BaseBottomSheetModalViewModel(open var tag: String) : ViewModel() {

    var removeModelFromMapOnModalDismiss: Boolean = true

    override fun onCleared() {
        if(removeModelFromMapOnModalDismiss) BottomSheetModalsService.removeModalModelFromMap(tag)

        BottomSheetModalsService.modalCurrentlyShowing = false

        Log.d("TAG", "onCleared: ${BottomSheetModalsService.modalModelsMap}")
    }
}