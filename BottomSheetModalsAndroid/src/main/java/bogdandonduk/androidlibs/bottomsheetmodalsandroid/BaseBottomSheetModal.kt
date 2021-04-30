package bogdandonduk.androidlibs.bottomsheetmodalsandroid

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import top.defaults.drawabletoolbox.DrawableBuilder

abstract class BaseBottomSheetModal : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.BottomSheetModalTheme)
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        dismiss()
    }

    fun expandSheet() {
        dialog?.setOnShowListener {
            BottomSheetBehavior.from(dialog!!.findViewById<FrameLayout>(R.id.design_bottom_sheet)!!).state = BottomSheetBehavior.STATE_EXPANDED
            BottomSheetModalsService.modalShowingCurrently = true
        }
    }

    fun transparifyBackground() {
        (view?.parent as View).run {
            setBackgroundColor(Color.TRANSPARENT)
        }
    }

    fun getBackgroundDrawable(backgroundColor: Int, cornerRadius: Int = 50) =
        DrawableBuilder()
            .rectangle()
            .cornerRadius(cornerRadius)
            .solidColor(backgroundColor)
            .build()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        expandSheet()
        transparifyBackground()
    }
}