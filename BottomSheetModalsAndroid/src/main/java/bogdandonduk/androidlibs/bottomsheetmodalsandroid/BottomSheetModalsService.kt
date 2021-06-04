package bogdandonduk.androidlibs.bottomsheetmodalsandroid

import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.BottomSheetModalsTagUtils
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base.BaseBottomSheetModalModel
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple.SimpleBottomSheetModalBuilder
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple.SimpleBottomSheetModalModel

object BottomSheetModalsService {
    internal val modalModelsMap = mutableMapOf<String, BaseBottomSheetModalModel>()
    @Volatile internal var modalCurrentlyShowing = false

    @Synchronized
    internal fun addModalModelToMap(tag: String, model: BaseBottomSheetModalModel) {
        if(modalModelsMap.containsKey(tag) && modalModelsMap[tag]!!::class.java != model::class.java)
            throw IllegalArgumentException("modalModelsMap already contains item with this tag but its type is different from passed ")
        else
            modalModelsMap[tag] = model
    }

    @PublishedApi
    @Suppress("UNCHECKED_CAST")
    internal fun <T : BaseBottomSheetModalModel> getModalModelFromMap(tag: String) = modalModelsMap[tag] as T?

    internal fun removeModalModelFromMap(tag: String) {
        modalModelsMap.remove(tag)
    }

    @Synchronized
    internal fun dismissAllMapModals(clear: Boolean = false) {
        modalModelsMap.run {
            forEach {
                it.value.modal?.dismiss()

                if(clear) remove(it.key)
            }
        }
    }

    inline fun updateSimpleModalModelAndShowIfVisible(tag: String, applyModificationOnlyIfVisible: Boolean = false, modification: (builder: SimpleBottomSheetModalBuilder) -> SimpleBottomSheetModalBuilder) {
        getModalModelFromMap<SimpleBottomSheetModalModel>(tag).run {
            val builder = getSimpleModalBuilder(tag)

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

    fun getSimpleModalBuilder(tag: String = BottomSheetModalsTagUtils.generateRandomTag(), restorePreviousState: Boolean = true) = SimpleBottomSheetModalBuilder(tag, restorePreviousState)
}