package bogdandonduk.androidlibs.bottomsheetmodalsandroid

import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.BottomSheetModalsTaggingUtils
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base.BaseBottomSheetModalModel
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple.BottomSheetModalBuilder
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple.BottomSheetModalModel

object BottomSheetModalsService {
    internal val modalModelsMap = mutableMapOf<String, BaseBottomSheetModalModel>()
    @Volatile internal var modalCurrentlyShowing = false

    @Synchronized
    internal fun addModalModel(tag: String, model: BaseBottomSheetModalModel) {
        if(modalModelsMap.containsKey(tag) && modalModelsMap[tag]!!::class.java != model::class.java)
            throw IllegalArgumentException("modalModelsMap already contains item with this tag but its type is different from passed ")
        else
            modalModelsMap[tag] = model
    }

    @PublishedApi
    @Suppress("UNCHECKED_CAST")
    internal fun <T : BaseBottomSheetModalModel> getModalModel(tag: String) = modalModelsMap[tag] as T?

    internal fun removeModalModel(tag: String) {
        modalModelsMap.remove(tag)
    }

    @Synchronized
    internal fun dismissAllModals(removeModels: Boolean = true) {
        modalModelsMap.run {
            forEach {
                it.value.modal?.dismiss()

                if(removeModels) remove(it.key)
            }
        }
    }

    inline fun updateSimpleModalModelAndShowIfVisible(
        tag: String,
        applyModificationOnlyIfVisible: Boolean = false,
        modification: (builder: BottomSheetModalBuilder) -> BottomSheetModalBuilder
    ) {
        getModalModel<BottomSheetModalModel>(tag).run {
            val builder = buildModal(tag)

            if(this != null)
                if(!applyModificationOnlyIfVisible) {
                    modification.invoke(builder)
                    modal?.drawContent()
                } else if(applyModificationOnlyIfVisible && modal != null) {
                    modification.invoke(builder)
                    modal!!.drawContent()
                }
        }
    }

    fun buildModal(tag: String = BottomSheetModalsTaggingUtils.generateRandomTag(), restorePreviousState: Boolean = true) = BottomSheetModalBuilder(tag, restorePreviousState)
}