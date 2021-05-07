package bogdandonduk.androidlibs.bottomsheetmodalsandroidlibrary

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.anatomy.AdditionalButtonsSection
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.anatomy.ButtonItem
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.anatomy.TextItem
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple.SimpleBottomSheetModalArgReference
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
            column.addView(
                LinearLayout(this).apply {
                    orientation = LinearLayout.HORIZONTAL

                    addView(ImageView(this@MainActivity).apply { setImageResource(R.drawable.ic_baseline_bolt_24) })
                    addView(TextView(this@MainActivity).apply { text = "I am dynamic text" })
                }
            )

            with(BottomSheetModalsService) {
                showSimpleModal(
                        fragmentManager = supportFragmentManager,
                        backgroundColor = Color.WHITE,
                        title = TextItem(null, "Title", Color.BLACK),
                        textItems = mutableListOf<TextItem>().apply { add(TextItem(getDrawable(R.drawable.ic_baseline_bolt_24), "Text", Color.BLACK)) },
                        positiveButton = ButtonItem("Confirm", Color.BLACK, positiveLambda),
                        negativeButton = ButtonItem("Cancel", Color.BLACK, negativeLambda),
                        additionalButtonsSection = AdditionalButtonsSection(getDrawable(R.drawable.ic_overflow_menu), "New options", mutableListOf<ButtonItem>().apply {
                            add(ButtonItem("Button 1", Color.BLACK) { _: View, _: BottomSheetDialogFragment ->

                            })
                        }),
                        tag = "some_modal"
                    )
            }
        }
//            BottomSheetModalsService.showListModal(
//                fragmentManager = supportFragmentManager,
//                backgroundColor = Color.BLACK,
//                title = "TITLE",
//                textColor = Color.WHITE,
//                listItems = mutableListOf<ListBottomSheetModal.Item>().apply {
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//                    add(ListBottomSheetModal.Item(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_content_copy_24, null), "A NEW ITEM"))
//
//                },
//                positiveButtonText = "Confirm",
//                positiveButtonTextColor = Color.GREEN,
//                positiveButtonClickAction = positiveLambda,
//                negativeButtonText = "Cancel",
//                negativeButtonTextColor = Color.RED,
//                negativeButtonClickAction = negativeLambda,
//                tag = "some_modal"
//            )
//        }

        BottomSheetModalsService.getModalArgReferenceForTag<SimpleBottomSheetModalArgReference>("some_modal")?.run {
            positiveButton.clickAction = positiveLambda
            negativeButton.clickAction = negativeLambda
        }
    }
}