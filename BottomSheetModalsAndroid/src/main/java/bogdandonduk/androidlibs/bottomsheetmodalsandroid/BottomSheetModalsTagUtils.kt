package bogdandonduk.androidlibs.bottomsheetmodalsandroid

import kotlin.random.Random

object BottomSheetModalsTagUtils {
    internal val transientTagRegistry = mutableListOf<String>()
    
    fun generateRandomTag() : String {
        var tag = "modal_${Random.nextInt(0, 1000000000)}"

        while(transientTagRegistry.contains(tag)) {
            tag = "modal_${Random.nextInt(0, 1000000000)}"
        }

        return tag
    }
}