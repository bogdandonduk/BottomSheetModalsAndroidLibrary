package bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.anatomy.BottomSheetModalAnatomy
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.databinding.LayoutOverflowPopupWindowBinding
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.databinding.LayoutSimpleBottomSheetModalTextItemBinding
import bogdandonduk.androidlibs.commonpreferencesutilsandroid.VibratorService
import bogdandonduk.androidlibs.recyclerviewutilsandroid.RecyclerViewHost
import bogdandonduk.androidlibs.viewbindingutilsandroid.ViewBinder

class SimpleBottomSheetModalAdapter(
    var title: BottomSheetModalAnatomy.TextItem,
    var textItems: MutableList<BottomSheetModalAnatomy.TextItem>,
    var hostActivity: FragmentActivity,
    var touchHolder: View,
    var modalContextPopupMenu: BottomSheetModalAnatomy.Popup.ModalContext.Menu
) : RecyclerView.Adapter<SimpleBottomSheetModalAdapter.SimpleBottomSheetModalTextViewHolder>() {
    lateinit var hostRecyclerView: RecyclerView

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        hostRecyclerView = recyclerView
    }

    @SuppressLint("ClickableViewAccessibility", "InflateParams")
    inner class SimpleBottomSheetModalTextViewHolder(
        override val viewBindingInitialization: () -> LayoutSimpleBottomSheetModalTextItemBinding,
        override var viewBinding: LayoutSimpleBottomSheetModalTextItemBinding? = viewBindingInitialization.invoke()
    ) : RecyclerView.ViewHolder(viewBinding!!.root), ViewBinder<LayoutSimpleBottomSheetModalTextItemBinding>, RecyclerViewHost {

        init {
            getCurrentViewBinding().root.setOnTouchListener { v, event ->
                touchHolder.dispatchTouchEvent(event)

                false
            }

            getCurrentViewBinding().root.run {
                setOnLongClickListener {
                    if(textItems.isNotEmpty()) {
                        VibratorService.vibrateOneShot(hostActivity, 50)

                        val popupWindow = PopupWindow(null, ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT, true)

                        val view = LayoutOverflowPopupWindowBinding.inflate(hostActivity.layoutInflater, null, false).apply {
                            layoutOverflowPopupWindowContentContainerConstraintLayout.background = BottomSheetModalAnatomy.Background.getPopupBackgroundDrawable(modalContextPopupMenu.backgroundColor)

                            initializeList(
                                recyclerView = layoutOverflowPopupWindowTextContainerRecyclerView,
                                adapter = BottomSheetModalAnatomy.Popup.Adapter(
                                    items = modalContextPopupMenu.items,
                                    hostActivity = hostActivity,
                                    hostPopupWindow = popupWindow
                                ),
                                layoutManager = LinearLayoutManager(hostActivity)
                            )
                        }.root

                        popupWindow.contentView = view

                        popupWindow.showAsDropDown(hostRecyclerView, 100, 0)
                    }

                    true
                }
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = SimpleBottomSheetModalTextViewHolder(viewBindingInitialization = { LayoutSimpleBottomSheetModalTextItemBinding.inflate(hostActivity.layoutInflater, parent, false) })

    override fun onBindViewHolder(holder: SimpleBottomSheetModalTextViewHolder, position: Int) {
        when(position) {
            0 -> {
                holder.getCurrentViewBinding().run {
                    layoutSimpleBottomSheetModalItemIconContainerLinearLayout.visibility = View.GONE
                    layoutSimpleBottomSheetModalItemIconImageView.setImageDrawable(null)

                    layoutSimpleBottomSheetModalItemTextTextView.let {
                        with(title) {
                            it.textSize = 24f
                            it.setTextColor(textColor)
                            it.setTypeface(null, Typeface.BOLD)

                            it.text = text
                        }
                    }

                    layoutSimpleBottomSheetModalItemUnderplaceholderLinearLayout.visibility = View.VISIBLE
                }
            }
            else -> {
                holder.getCurrentViewBinding().run {
                    textItems[position - 1].let { item ->
                        if(item.icon == null) {
                            layoutSimpleBottomSheetModalItemIconContainerLinearLayout.visibility = View.GONE

                            layoutSimpleBottomSheetModalItemIconImageView.setImageDrawable(null)
                        } else {
                            layoutSimpleBottomSheetModalItemIconContainerLinearLayout.visibility = View.VISIBLE

                            layoutSimpleBottomSheetModalItemIconImageView.setImageDrawable(item.icon)
                        }

                        layoutSimpleBottomSheetModalItemTextTextView.run {
                            textSize = 16f
                            setTextColor(item.textColor)
                            setTypeface(null, Typeface.NORMAL)

                            text = item.text
                        }

                        layoutSimpleBottomSheetModalItemUnderplaceholderLinearLayout.visibility = View.GONE
                    }

                }

            }
        }
    }

    override fun getItemCount() = textItems.size + 1
}