package bogdandonduk.androidlibs.bottomsheetmodalsandroid

import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple.SimpleBottomSheetModal
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple.SimpleBottomSheetModalArgReference
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

object BottomSheetModalsService {
    const val KEY_ARGUMENT_TAG = "key_argument_tag"

    private val simpleModalsArgReferencesMap = mutableMapOf<String, SimpleBottomSheetModalArgReference>()

    @Volatile var modalShowingCurrently = false

    fun showSimpleModal(
        fragmentManager: FragmentManager,
        backgroundColor: Int,
        title: String,
        text: String,
        textColor: Int,
        titleColor: Int = textColor,
        positiveBtnText: String,
        positiveBtnTextColor: Int,
        positiveBtnClickAction: (view: View, modal: BottomSheetDialogFragment) -> Unit,
        negativeBtnText: String,
        negativeBtnTextColor: Int = positiveBtnTextColor,
        negativeBtnClickAction: (view: View, modal: BottomSheetDialogFragment) -> Unit,
        tag: String
    ) {
        if(!modalShowingCurrently) {
            if(!simpleModalsArgReferencesMap.containsKey(tag))
                simpleModalsArgReferencesMap[tag] = SimpleBottomSheetModalArgReference(
                    backgroundColor = backgroundColor,
                    title = title,
                    text = text,
                    textColor = textColor,
                    titleColor = titleColor,
                    positiveBtnText = positiveBtnText,
                    positiveBtnTextColor = positiveBtnTextColor,
                    positiveBtnClickAction = positiveBtnClickAction,
                    negativeBtnText = negativeBtnText,
                    negativeBtnTextColor = negativeBtnTextColor,
                    negativeBtnClickAction = negativeBtnClickAction
                )

            val modal = SimpleBottomSheetModal()
                modal.arguments = bundleOf(KEY_ARGUMENT_TAG to tag)

                modal.show(fragmentManager, tag)
        }
    }

    fun getSimpleModalArgReferenceForTag(tag: String) = simpleModalsArgReferencesMap[tag]

    fun removeSimpleModalArgReferenceForTag(tag: String) {
        simpleModalsArgReferencesMap.remove(tag)
    }
}