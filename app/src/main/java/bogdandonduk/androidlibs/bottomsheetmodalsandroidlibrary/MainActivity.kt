package bogdandonduk.androidlibs.bottomsheetmodalsandroidlibrary

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.anatomy.Button
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.anatomy.Text
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple.SimpleBottomSheetModal
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onResume() {
        super.onResume()

        Log.d("TAG", "onResume: ")
        BottomSheetModalsService.updateSimpleModalModelAndShowIfVisible("short_light", true) {
            Log.d("TAG", "onResume: HERE")
            it.title.color = Color.RED
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val positiveLambda: ((view: View, modal: BottomSheetDialogFragment) -> Unit) = { view: View, bottomSheetDialogFragment: BottomSheetDialogFragment ->
            bottomSheetDialogFragment.dismiss()
        }

        val negativeLambda: ((view: View, modal: BottomSheetDialogFragment) -> Unit) = { view: View, bottomSheetDialogFragment: BottomSheetDialogFragment ->
            bottomSheetDialogFragment.dismiss()
        }

        launch_short_dark.setOnClickListener {
            SimpleBottomSheetModal.Builder("short_dark")
                .setAppearance {
                    it.backgroundColor = Color.BLACK
                    it.dividerLinesColor = Color.WHITE

                    it
                }
                .setTitle {
                    Text("Awesome title", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                }
                .setTextContent {
                    it["a"] = Text("Text 1", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["b"] = Text("Text 2", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["c"] = Text("Text 3", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))

                    it
                }
                .setPositiveButton {
                    it.textColor = Color.WHITE
                    it
                }
                .setNegativeButton {
                    it.textColor = Color.WHITE
                    it
                }
                .setContextMenu {
                    it.buttons["a"] = Button("Button 1", Color.RED)

                    it
                }
                .show(supportFragmentManager, removeModelFromMapOnModalDismiss = false)
        }

        launch_short_light.setOnClickListener {
            SimpleBottomSheetModal.Builder("short_light")
                .setAppearance {
                    it.backgroundColor = Color.WHITE
                    it.dividerLinesColor = Color.BLACK

                    it
                }
                .setTitle {
                    Text("Awesome title", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                }
                .setTextContent {
                    it["a"] = Text("Text 1", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["b"] = Text("Text 2", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["c"] = Text("Text 3", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))

                    it
                }
                .setPositiveButton {
                    it.textColor = Color.BLACK
                    it
                }
                .setNegativeButton {
                    it.textColor = Color.BLACK
                    it
                }
                .setContextMenu {
                    it.buttons["a"] = Button("Button 1", Color.RED)

                    it
                }
                .setOverflowMenu {
                    it.overflowIconTintColor = Color.BLACK
                    it.buttons["a"] = Button("Button 1", Color.CYAN)

                    it
                }
                .show(supportFragmentManager, removeModelFromMapOnModalDismiss = false)
        }

        launch_long_dark.setOnClickListener {
            SimpleBottomSheetModal.Builder("long_dark")
                .setAppearance {
                    it.backgroundColor = Color.BLACK
                    it.dividerLinesColor = Color.WHITE

                    it
                }
                .setTitle {
                    Text("Awesome title", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                }
                .setTextContent {
                    it["a"] = Text("Text 1", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["b"] = Text("Text 2", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["c"] = Text("Text 3", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["d"] = Text("Text 4", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["e"] = Text("Text 5", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["f"] = Text("Text 6", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["g"] = Text("Text 7", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["h"] = Text("Text 8", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["i"] = Text("Text 9", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["j"] = Text("Text 10", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["k"] = Text("Text 11", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["l"] = Text("Text 12", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["m"] = Text("Text 13", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["n"] = Text("Text 14", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["o"] = Text("Text 15", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["p"] = Text("Text 16", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["q"] = Text("Text 17", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["r"] = Text("Text 18", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["s"] = Text("Text 19", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["t"] = Text("Text 20", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["u"] = Text("Text 21", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["v"] = Text("Text 22", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["w"] = Text("Text 23", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["x"] = Text("Text 24", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["y"] = Text("Text 25", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["x"] = Text("Text 26", Color.WHITE, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))

                    it
                }
                .setPositiveButton {
                    it.textColor = Color.WHITE
                    it
                }
                .setNegativeButton {
                    it.textColor = Color.WHITE
                    it
                }
                .setContextMenu {
                    it.buttons["a"] = Button("Button 1", Color.RED)

                    it
                }
                .setOverflowMenu {
                    it.overflowIconTintColor = Color.WHITE
                    it.buttons["a"] = Button("Button 1", Color.CYAN)

                    it
                }
                .show(supportFragmentManager, removeModelFromMapOnModalDismiss = false)
        }

        launch_long_light.setOnClickListener {
            SimpleBottomSheetModal.Builder("long_light")
                .setAppearance {
                    it.backgroundColor = Color.WHITE
                    it.dividerLinesColor = Color.BLACK

                    it
                }
                .setTitle {
                    Text("Awesome title", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                }
                .setTextContent {
                    it["a"] = Text("Text 1", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["b"] = Text("Text 2", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["c"] = Text("Text 3", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["d"] = Text("Text 4", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["e"] = Text("Text 5", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["f"] = Text("Text 6", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["g"] = Text("Text 7", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["h"] = Text("Text 8", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["i"] = Text("Text 9", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["j"] = Text("Text 10", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["k"] = Text("Text 11", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["l"] = Text("Text 12", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["m"] = Text("Text 13", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["n"] = Text("Text 14", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["o"] = Text("Text 15", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["p"] = Text("Text 16", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["q"] = Text("Text 17", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["r"] = Text("Text 18", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["s"] = Text("Text 19", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["t"] = Text("Text 20", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["u"] = Text("Text 21", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["v"] = Text("Text 22", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["w"] = Text("Text 23", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["x"] = Text("Text 24", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["y"] = Text("Text 25", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))
                    it["x"] = Text("Text 26", Color.BLACK, ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null))

                    it
                }
                .setPositiveButton {
                    it.textColor = Color.BLACK

                    it
                }
                .setNegativeButton {
                    it.textColor = Color.BLACK
                    it.text = "shfkjssgtehdfgshdfjdfhdfhdfhdfhdfjdfdf"

                    it
                }
                .setContextMenu {
                    it.buttons["a"] = Button("Button 1", Color.RED)

                    it
                }
                .setOverflowMenu {
                    it.overflowIconTintColor = Color.BLACK
                    it.buttons["a"] = Button("Button 1", Color.CYAN)

                    it
                }
                .show(supportFragmentManager, removeModelFromMapOnModalDismiss = false)
        }
    }
}