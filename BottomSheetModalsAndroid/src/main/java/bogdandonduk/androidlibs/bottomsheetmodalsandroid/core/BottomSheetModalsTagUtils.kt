package bogdandonduk.androidlibs.bottomsheetmodalsandroid.core

import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService
import kotlin.random.Random

object BottomSheetModalsTagUtils {
    internal val transientTagRegistry = mutableListOf<String>()
    
    fun generateRandomTag() : String {
        var tag = "modal_${Random.nextInt(0, 100000000)}"

        while(BottomSheetModalsService.modalModelsMap.containsKey(tag) || transientTagRegistry.contains(tag)) {
            tag = "modal_${Random.nextInt(0, 100000000)}"
        }

        return tag
    }
}