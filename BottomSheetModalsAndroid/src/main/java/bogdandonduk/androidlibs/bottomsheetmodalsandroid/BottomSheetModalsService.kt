package bogdandonduk.androidlibs.bottomsheetmodalsandroid

import android.os.Handler
import android.os.Looper
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.RedrawingModal
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base.BaseBottomSheetModal
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base.BaseBottomSheetModalArgReference
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple.SimpleBottomSheetModal

object BottomSheetModalsService {
    private val modalArgReferencesMap = mutableMapOf<String, BaseBottomSheetModalArgReference>()

    @Volatile var modalShowingCurrently = false

    val handler = Handler(Looper.getMainLooper())

    fun getSimpleModalBuilder(tag: String): SimpleBottomSheetModal.Builder = SimpleBottomSheetModal.Builder(tag)

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
}