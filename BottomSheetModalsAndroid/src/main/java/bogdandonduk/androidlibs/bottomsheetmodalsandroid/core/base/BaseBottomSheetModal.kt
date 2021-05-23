package bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetModal : BottomSheetDialogFragment() {
    lateinit var fragmentContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentContext = requireContext()

        setStyle(STYLE_NORMAL, R.style.BottomSheetModalStyle)
    }

    override fun onCancel(dialog: DialogInterface) {
        dialog.dismiss()
    }

    private fun expandSheet() {
        BottomSheetBehavior.from(dialog!!.findViewById<FrameLayout>(R.id.design_bottom_sheet)!!).state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun transparifyBackground() {
        (view?.parent as View).run {
            setBackgroundColor(Color.TRANSPARENT)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        transparifyBackground()
        dialog?.setOnShowListener {
            BottomSheetModalsService.modalCurrentlyShowing = true
            expandSheet()
        }

        drawContent()
    }

    override fun onDestroy() {
        super.onDestroy()

        BottomSheetModalsService.getModalModelFromMap<BaseBottomSheetModalModel>(tag!!)?.modal = null
    }

    abstract fun drawContent()

    abstract fun initializeAppearance()
}