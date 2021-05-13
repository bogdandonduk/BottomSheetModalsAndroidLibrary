package bogdandonduk.androidlibs.bottomsheetmodalsandroidlibrary

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.anatomy.BottomSheetModalAnatomy
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val positiveLambda: ((view: View, modal: BottomSheetDialogFragment) -> Unit) = { view: View, bottomSheetDialogFragment: BottomSheetDialogFragment ->
            bottomSheetDialogFragment.dismiss()
        }

        val negativeLambda: ((view: View, modal: BottomSheetDialogFragment) -> Unit) = { view: View, bottomSheetDialogFragment: BottomSheetDialogFragment ->
            bottomSheetDialogFragment.dismiss()
        }

        launch_short_light.setOnClickListener {
            val textItems = mutableListOf<BottomSheetModalAnatomy.TextItem>().apply {
                add(
                    BottomSheetModalAnatomy.TextItem(
                        "This will delete selected items",
                        Color.BLACK,
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_baseline_content_copy_24,
                            null
                        )
                    )
                )

                add(
                    BottomSheetModalAnatomy.TextItem(
                        "It cannot be undone",
                        Color.BLACK,
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_baseline_content_copy_24,
                            null
                        )
                    )
                )
            }

            BottomSheetModalsService.startBuildingSimpleModal("some_modal")
                .setTitle(
                    BottomSheetModalAnatomy.TextItem(
                        text = "Are you sure?",
                        textColor = Color.BLACK
                    )
                )
                .setTextItems(textItems)
                .setPositiveButton(
                    BottomSheetModalAnatomy.ButtonItem(
                        "Confirm",
                        Color.CYAN,
                        positiveLambda
                    )
                )
                .setNegativeButton(
                    BottomSheetModalAnatomy.ButtonItem(
                        "Cancel",
                        Color.CYAN,
                        negativeLambda
                    )
                )
                .setModalContextPopupMenu(
                   BottomSheetModalAnatomy.Popup.ModalContext.getMenuBuilder()
                       .addItems(
                           items = mutableListOf<BottomSheetModalAnatomy.Popup.Item>().apply {
                               add(
                                   BottomSheetModalAnatomy.Popup.ModalContext.DefaultItems.ItemCopy(
                                       context = this@MainActivity,
                                       text = "Copy entire text into the amazing clipboard",
                                       textColor = Color.BLACK,
                                       textItems = textItems
                                   )
                               )
                           }
                       )
                       .setBackgroundColor(Color.WHITE)
                       .build()
                )
                .setOverflowButtonsContextPopupMenu(
                    BottomSheetModalAnatomy.Popup.OverflowButtonsContext.getMenuBuilder()
                        .addItems(
                            items = mutableListOf<BottomSheetModalAnatomy.Popup.Item>().apply {
                                add(
                                    BottomSheetModalAnatomy.Popup.Item(
                                        text = "Option 1",
                                        textColor = Color.BLACK
                                    ) { view: View, popupWindow: PopupWindow ->

                                    }
                                )
                                add(
                                    BottomSheetModalAnatomy.Popup.Item(
                                        text = "Option 2",
                                        textColor = Color.BLACK
                                    ) { view: View, popupWindow: PopupWindow ->

                                    }
                                )

                            }
                        )
                        .build()
                )
                .show(fragmentManager = supportFragmentManager)
        }

    }
}