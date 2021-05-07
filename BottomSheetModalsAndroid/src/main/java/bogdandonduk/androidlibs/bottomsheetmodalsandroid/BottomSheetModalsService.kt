package bogdandonduk.androidlibs.bottomsheetmodalsandroid

import android.content.DialogInterface
import androidx.annotation.ColorInt
import androidx.fragment.app.FragmentManager
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.anatomy.AdditionalButtonsSection
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.anatomy.ButtonItem
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.anatomy.TextItem
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.RedrawingModal
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base.BaseBottomSheetModal
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base.BaseBottomSheetModalArgReference
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple.SimpleBottomSheetModal
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple.SimpleBottomSheetModalArgReference

object BottomSheetModalsService {
    private val modalArgReferencesMap = mutableMapOf<String, BaseBottomSheetModalArgReference>()

    private val modalsMap = mutableMapOf<String, BaseBottomSheetModal>()

    @Volatile var modalShowingCurrently = false

    fun startBuildingSimpleModal(tag: String) = SimpleBottomSheetModal.Builder(tag)

    fun showSimpleModal(
        fragmentManager: FragmentManager,
        @ColorInt backgroundColor: Int,
        title: TextItem,
        textItems: MutableList<TextItem>,
        positiveButton: ButtonItem,
        negativeButton: ButtonItem,
        additionalButtonsSection: AdditionalButtonsSection = AdditionalButtonsSection(null, "More options", mutableListOf()),
        tag: String,
        onCancelAction: ((modal: DialogInterface) -> Unit)? = null,
        onDismissAction: ((modal: DialogInterface) -> Unit)? = null
    ) {
        if(!modalShowingCurrently) {
            addModalArgReferenceForTag(tag, SimpleBottomSheetModalArgReference(
                backgroundColor = backgroundColor,
                title = title,
                textItems = textItems,
                positiveButton = positiveButton,
                negativeButton = negativeButton,
                additionalButtonsSection = additionalButtonsSection,
                onCancelAction = onCancelAction,
                onDismissAction = onDismissAction
            ))

            SimpleBottomSheetModal().show(fragmentManager, tag)
        }
    }

    fun addModalArgReferenceForTag(tag: String, argReference: BaseBottomSheetModalArgReference) {
        if(modalArgReferencesMap.containsKey(tag) && modalArgReferencesMap[tag]!!::class.java != argReference::class.java)
            throw IllegalArgumentException("modalArgReferencesMap already contains item with this tag but its type is different from type of passed argReference")
        else if(!modalArgReferencesMap.containsKey(tag))
            modalArgReferencesMap[tag] = argReference
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : BaseBottomSheetModalArgReference> getModalArgReferenceForTag(tag: String) = modalArgReferencesMap[tag] as T?

    fun removeModalArgReferenceForTag(tag: String) {
        modalArgReferencesMap.remove(tag)
    }

    fun addModalForTag(tag: String, modal: BaseBottomSheetModal, override: Boolean = true) {
        if(override || !modalsMap.containsKey(tag))
            modalsMap[tag] = modal
    }

    fun <BaseBottomSheetModalArgReference> getModalForTag(tag: String) = modalsMap[tag]

    fun removeModalForTag(tag: String) {
        modalsMap.remove(tag)
    }

    fun <BaseBottomSheetModalArgReference> getRedrawingModalForTag(tag: String) = modalsMap[tag] as RedrawingModal

}