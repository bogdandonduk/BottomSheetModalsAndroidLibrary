package bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.PopupWindow
import androidx.annotation.ColorInt
import androidx.appcompat.widget.TooltipCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.R
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.anatomy.BottomSheetModalAnatomy
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base.BaseBottomSheetModal
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.databinding.LayoutSimpleBottomSheetModalBinding
import bogdandonduk.androidlibs.recyclerviewutilsandroid.RecyclerViewHost
import bogdandonduk.androidlibs.viewbindingutilsandroid.LateinitViewBinder
import bogdandonduk.androidlibs.viewmodelutilsandroid.ViewModelHost

class SimpleBottomSheetModal : BaseBottomSheetModal(), ViewModelHost<SimpleBottomSheetModalViewModel>,
    LateinitViewBinder<LayoutSimpleBottomSheetModalBinding>, RecyclerViewHost {
    override lateinit var viewBinding: LayoutSimpleBottomSheetModalBinding

    override var viewModel: SimpleBottomSheetModalViewModel? = null

    override val viewModelInitialization: () -> SimpleBottomSheetModalViewModel = {
        ViewModelProvider(
            viewModelStore,
            SimpleBottomSheetModalViewModel.Factory(
                BottomSheetModalsService.getModalArgReferenceForTag(tag!!)!!,
                tag!!
            )
        ).get(SimpleBottomSheetModalViewModel::class.java)
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)

        getCurrentViewModel().argReference.onCancelAction?.invoke(dialog)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        getCurrentViewModel().argReference.onDismissAction?.invoke(dialog)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun redraw() {
        with(viewBinding) {
            getCurrentViewModel().let {
                it.argReference.modal = this@SimpleBottomSheetModal

                layoutSimpleBottomSheetModalContentContainerConstraintLayout.background = BottomSheetModalAnatomy.Background.getModalBackgroundDrawable(it.argReference.backgroundColor)

                initializeList(
                    layoutSimpleBottomSheetModalTextContainerRecyclerView,
                    adapter = SimpleBottomSheetModalAdapter(
                        title = it.argReference.title,
                        textItems = it.argReference.textItems,
                        hostActivity = requireActivity(),
                        touchHolder = layoutSimpleBottomSheetModalTouchConstraintLayout,
                        modalContextPopupMenu = it.argReference.modalContextPopupMenu
                    ),
                    layoutManager = GridLayoutManager(fragmentContext, 1)
                )

                layoutSimpleBottomSheetModalPositiveButton.run {
                    setTextColor(it.argReference.positiveButton.textColor)
                    text = it.argReference.positiveButton.text

                    setOnClickListener { view ->
                        it.argReference.positiveButton.clickAction.invoke(view, this@SimpleBottomSheetModal)
                    }

                    post {
                        if(layout.getEllipsisCount(1) > 0) TooltipCompat.setTooltipText(this, it.argReference.positiveButton.text)
                    }
                }

                layoutSimpleBottomSheetModalNegativeButton.run {
                    setTextColor(it.argReference.negativeButton.textColor)
                    text = it.argReference.negativeButton.text

                    setOnClickListener { view ->
                        it.argReference.negativeButton.clickAction.invoke(view, this@SimpleBottomSheetModal)
                    }

                    post {
                        if(layout.getEllipsisCount(1) > 0) TooltipCompat.setTooltipText(this, it.argReference.negativeButton.text)
                    }
                }

                layoutSimpleBottomSheetModalButtonDividerLinearLayout.setBackgroundColor(it.argReference.title.textColor)

                if(it.argReference.overflowButtonsContextPopupMenu.items.isNotEmpty()) {
                    DrawableCompat.setTint(layoutSimpleBottomSheetModalMoreOptionsButtonIconImageView.drawable, it.argReference.title.textColor)

                    layoutSimpleBottomSheetModalButtonDivider2ContainerConstraintLayout.visibility = View.VISIBLE

                    layoutSimpleBottomSheetModalButtonDivider2LinearLayout.setBackgroundColor(it.argReference.title.textColor)
                    layoutSimpleBottomSheetModalMoreOptionsButtonContainerCardView.visibility = View.VISIBLE

                    layoutSimpleBottomSheetModalMoreOptionsButtonContainerConstraintLayout.run {
                        post {
                            TooltipCompat.setTooltipText(this, it.argReference.overflowButtonsContextPopupMenu.overflowButtonTooltipText)
                        }
                    }

                    layoutSimpleBottomSheetModalMoreOptionsButtonContainerConstraintLayout.run {
                        setOnClickListener {
                            PopupWindow(layoutInflater.inflate(R.layout.layout_overflow_popup_window, null, false), 300, 300, true).showAsDropDown(this, 30, 0)

                        }
                    }
                }

                if(it.argReference.modalContextPopupMenu.items.isNotEmpty()) {
                    layoutSimpleBottomSheetModalTouchConstraintLayout.setOnClickListener {

                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = LayoutSimpleBottomSheetModalBinding.inflate(inflater, container, false)

        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        redraw()
    }
    
    class Builder internal constructor(var tag: String) {
        private var argReference = SimpleBottomSheetModalArgReference()

        fun setBackgroundColor(@ColorInt color: Int) : Builder = this.apply { argReference.backgroundColor = color }

        fun setTitle(title: BottomSheetModalAnatomy.TextItem) : Builder = this.apply { argReference.title = title }

        fun setTextItems(textsItems: MutableList<BottomSheetModalAnatomy.TextItem>) : Builder = this.apply { argReference.textItems = textsItems }

        fun setPositiveButton(buttonItem: BottomSheetModalAnatomy.ButtonItem) : Builder = this.apply { argReference.positiveButton = buttonItem }

        fun setNegativeButton(buttonItem: BottomSheetModalAnatomy.ButtonItem) : Builder = this.apply { argReference.negativeButton = buttonItem }

        fun setModalContextPopupMenu(menu: BottomSheetModalAnatomy.Popup.ModalContext.Menu) = this.apply { argReference.modalContextPopupMenu = menu }

        fun setOverflowButtonsContextPopupMenu(menu: BottomSheetModalAnatomy.Popup.OverflowButtonsContext.Menu) = this.apply { argReference.overflowButtonsContextPopupMenu = menu }

        fun setOnCancelAction(action: (modal: DialogInterface) -> Unit) : Builder = this.apply { argReference.onCancelAction = action }

        fun setOnDismissAction(action: (modal: DialogInterface) -> Unit) : Builder = this.apply { argReference.onDismissAction = action }

        fun show(fragmentManager: FragmentManager) {
            if(!BottomSheetModalsService.modalShowingCurrently) {
                BottomSheetModalsService.addModalArgReferenceForTag(tag, argReference)

                SimpleBottomSheetModal().show(fragmentManager, tag)
            }
        }
    }
}