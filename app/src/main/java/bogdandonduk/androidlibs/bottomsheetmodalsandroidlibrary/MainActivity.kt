package bogdandonduk.androidlibs.bottomsheetmodalsandroidlibrary

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val positiveLambda: ((view: View, modal: BottomSheetDialogFragment) -> Unit) = { view: View, bottomSheetDialogFragment: BottomSheetDialogFragment ->
            launchText.text = "YES"
        }

        val negativeLambda: ((view: View, modal: BottomSheetDialogFragment) -> Unit) = { view: View, bottomSheetDialogFragment: BottomSheetDialogFragment ->
            launchText.text = "NO"
        }

        launchText.setOnClickListener {
            BottomSheetModalsService.showSimpleModal(
                supportFragmentManager,
                Color.BLACK,
                "TITLE",
                "TEXTrre8r7wn0twe89rwy09dey8qwrybwufybtriweynfoisuiofhilgvhsklfgioerutunowinyto8weutwiutiowutioewrhtioewrumiotewuorwmiurhwiurywiufiuwbgfyiuwfupoaifposuigeyrioghdioghskjvbksjdfjhkus gkjshnsgfkusehnfiusgfiuhsiofapofhosiyhfoishfioshrgkshkjfvishviusl",
                Color.WHITE,
                positiveBtnText = "Confirm",
                positiveBtnTextColor = Color.GREEN,
                positiveBtnClickAction = positiveLambda,
                negativeBtnText = "Cancel",
                negativeBtnTextColor = Color.RED,
                negativeBtnClickAction = negativeLambda,
                tag = "some_modal"
            )
        }

        BottomSheetModalsService.getSimpleModalArgReferenceForTag("some_modal")?.run {
            positiveBtnClickAction = positiveLambda
            negativeBtnClickAction = negativeLambda
        }
    }
}