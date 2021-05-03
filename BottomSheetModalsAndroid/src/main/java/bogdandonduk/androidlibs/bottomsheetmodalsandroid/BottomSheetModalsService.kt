package bogdandonduk.androidlibs.bottomsheetmodalsandroid

import android.content.DialogInterface
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple.SimpleBottomSheetModal
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple.SimpleBottomSheetModalArgReference
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

object BottomSheetModalsService {
    val simpleModalsArgReferencesMap = mutableMapOf<String, SimpleBottomSheetModalArgReference>()

    @Volatile var modalShowingCurrently = false

    fun startBuildingSimpleModal(tag: String) = SimpleBottomSheetModal.Builder(tag)

    fun showSimpleModal(
        fragmentManager: FragmentManager,
        backgroundColor: Int,
        title: String,
        text: String,
        textColor: Int,
        titleColor: Int = textColor,
        positiveButtonText: String,
        positiveButtonTextColor: Int,
        positiveButtonClickAction: (view: View, modal: BottomSheetDialogFragment) -> Unit,
        negativeButtonText: String,
        negativeButtonTextColor: Int = positiveButtonTextColor,
        negativeButtonClickAction: (view: View, modal: BottomSheetDialogFragment) -> Unit,
        tag: String,
        onCancelAction: ((modal: DialogInterface) -> Unit)?  = null,
        onDismissAction: ((modal: DialogInterface) -> Unit)?  = null
    ) {
        if(!modalShowingCurrently) {
            if(!simpleModalsArgReferencesMap.containsKey(tag))
                simpleModalsArgReferencesMap[tag] = SimpleBottomSheetModalArgReference(
                    backgroundColor = backgroundColor,
                    title = title,
                    text = text,
                    textColor = textColor,
                    titleColor = titleColor,
                    positiveButtonText = positiveButtonText,
                    positiveButtonTextColor = positiveButtonTextColor,
                    positiveButtonClickAction = positiveButtonClickAction,
                    negativeButtonText = negativeButtonText,
                    negativeButtonTextColor = negativeButtonTextColor,
                    negativeButtonClickAction = negativeButtonClickAction,
                    onCancelAction = onCancelAction,
                    onDismissAction = onDismissAction
                )

            SimpleBottomSheetModal().show(fragmentManager, tag)
        }
    }

    fun getSimpleModalArgReferenceForTag(tag: String) = simpleModalsArgReferencesMap[tag]

    fun removeSimpleModalArgReferenceForTag(tag: String) {
        simpleModalsArgReferencesMap.remove(tag)
    }
}