package bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple

import android.content.DialogInterface
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.appcompat.widget.TooltipCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.anatomy.AdditionalButtonsSection
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.anatomy.ButtonItem
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.anatomy.TextItem
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

    override fun redraw() {

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

        with(viewBinding) {
            getCurrentViewModel().let {

                layoutSimpleBottomSheetModalContentConstraintLayout.background = getBackgroundDrawable(it.argReference.backgroundColor)

                initializeList(
                    layoutSimpleBottomSheetModalTextContainerRecyclerView,
                    adapter = SimpleBottomSheetModalAdapter(
                        title = it.argReference.title,
                        textItems = it.argReference.textItems,
                        hostActivity = requireActivity()
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

//                if(it.argReference.additionalButtonsSection.buttonItems.isNotEmpty()) {
                    layoutSimpleBottomSheetModalButtonDividerLinearLayout2.run {
                        visibility = View.VISIBLE
                        setBackgroundColor(it.argReference.title.textColor)
                    }

                    layoutSimpleBottomSheetModalMoreOptionsButtonContainerCardView.visibility = View.VISIBLE

                    layoutSimpleBottomSheetModalMoreOptionsButtonContainerConstraintLayout.run {
                        TooltipCompat.setTooltipText(this, it.argReference.additionalButtonsSection.overflowIconTooltipText)
                        setOnClickListener {

                        }
                    }
//                }
            }
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
    }
    
    class Builder(var tag: String) {
        private var argReference = SimpleBottomSheetModalArgReference()

        fun setBackgroundColor(@ColorInt color: Int) : Builder = this.apply { argReference.backgroundColor = color }

        fun setTitle(title: TextItem) : Builder = this.apply { argReference.title = title }

        fun setTextItems(textsItems: MutableList<TextItem>) : Builder = this.apply { argReference.textItems = textsItems }

        fun setPositiveButton(buttonItem: ButtonItem) : Builder = this.apply { argReference.positiveButton = buttonItem }

        fun setNegativeButton(buttonItem: ButtonItem) : Builder = this.apply { argReference.negativeButton = buttonItem }

        fun setAdditionalButtonsSection(buttonsSection: AdditionalButtonsSection) = this.apply { argReference.additionalButtonsSection = buttonsSection }

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