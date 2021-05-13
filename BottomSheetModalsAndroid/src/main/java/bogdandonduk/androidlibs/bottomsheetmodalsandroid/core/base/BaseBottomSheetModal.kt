package bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base

import android.content.Context
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.R
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.RedrawingModal
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import top.defaults.drawabletoolbox.DrawableBuilder
import top.defaults.drawabletoolbox.RippleDrawableBuilder

abstract class BaseBottomSheetModal() : BottomSheetDialogFragment(), RedrawingModal {

    lateinit var fragmentContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentContext = requireContext()

        setStyle(STYLE_NORMAL, R.style.BottomSheetModalStyle)
    }

    override fun onCancel(dialog: DialogInterface) {
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        expandSheet()
        transparifyBackground()
    }
}