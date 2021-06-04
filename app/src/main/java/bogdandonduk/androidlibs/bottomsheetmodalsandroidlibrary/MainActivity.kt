package bogdandonduk.androidlibs.bottomsheetmodalsandroidlibrary

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.BottomSheetModalsTagUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var tag: String = BottomSheetModalsTagUtils.generateRandomTag()
    var tag2: String = BottomSheetModalsTagUtils.generateRandomTag()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        launch_short_light.setOnClickListener {
            Log.d("TAG", "onCreate: " + BottomSheetModalsService.getSimpleModalBuilder(tag, true).show(supportFragmentManager, saveStateAfterDismissal = true))
        }

        launch_short_dark.setOnClickListener {
            BottomSheetModalsService.getSimpleModalBuilder(tag).setBackgroundColor { Color.MAGENTA }.save()
        }
    }
}