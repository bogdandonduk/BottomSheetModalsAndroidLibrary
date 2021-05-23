package bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.TooltipCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsExtensionVocabulary
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsTagUtils
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.anatomy.*
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.anatomy.ContextMenu
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base.BaseBottomSheetModal
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.compose.ButtonHostModal
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.databinding.LayoutSimpleBottomSheetModalBinding
import bogdandonduk.androidlibs.commonpreferencesutilsandroid.GraphicsUtils
import bogdandonduk.androidlibs.recyclerviewutilsandroid.RecyclerViewHost
import bogdandonduk.androidlibs.viewbindingutilsandroid.LateinitViewBinder
import bogdandonduk.androidlibs.viewmodelutilsandroid.GenericViewModelFactory
import bogdandonduk.androidlibs.viewmodelutilsandroid.ViewModelHost
import top.defaults.drawabletoolbox.DrawableBuilder

internal class SimpleBottomSheetModal() : BaseBottomSheetModal(), ViewModelHost<SimpleBottomSheetModalViewModel>,
    LateinitViewBinder<LayoutSimpleBottomSheetModalBinding>, RecyclerViewHost, ButtonHostModal {
    override lateinit var viewBinding: LayoutSimpleBottomSheetModalBinding

    override var viewModel: SimpleBottomSheetModalViewModel? = null

    override val viewModelInitialization: () -> SimpleBottomSheetModalViewModel = {
        ViewModelProvider(
            viewModelStore,
            GenericViewModelFactory {
                SimpleBottomSheetModalViewModel(BottomSheetModalsService.getModalModelFromMap(tag!!)!!)
            }
        ).get(SimpleBottomSheetModalViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("TAG", "onCreate: ")

        getCurrentViewModel().run {
            model.modal = this@SimpleBottomSheetModal
            removeModelFromMapOnModalDismiss = requireArguments().getBoolean(BottomSheetModalsExtensionVocabulary.KEY_REMOVE_FROM_MAP_ON_DISMISS, true)
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

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)

        getCurrentViewModel().model.callbacks.onCancelAction?.invoke(dialog)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        getCurrentViewModel().model.callbacks.onDismissAction?.invoke(dialog)
    }

    override fun initializeButtons() {
        getCurrentViewModel().let {
            viewBinding.layoutSimpleBottomSheetModalPositiveButtonTextView.run {
                background =
                    DrawableBuilder()
                        .cornerRadius(30)
                        .ripple()
                        .rippleColor(GraphicsUtils.getRippleColor(getCurrentViewModel().model.appearance.backgroundColor))
                        .build()

                setTextColor(it.model.positiveButton.textColor)
                text = it.model.positiveButton.text

                setOnClickListener { view ->
                    it.model.positiveButton.clickAction?.invoke(view, this@SimpleBottomSheetModal)
                }

                post {
                    if(layout.getEllipsisCount(1) > 0) TooltipCompat.setTooltipText(this, it.model.positiveButton.text)
                }
            }

            viewBinding.layoutSimpleBottomSheetModalNegativeButtonTextView.run {
                background =
                    DrawableBuilder()
                        .cornerRadius(30)
                        .ripple()
                        .rippleColor(GraphicsUtils.getRippleColor(getCurrentViewModel().model.appearance.backgroundColor))
                        .build()

                setTextColor(it.model.negativeButton.textColor)
                text = it.model.negativeButton.text

                setOnClickListener { view ->
                    it.model.negativeButton.clickAction?.invoke(view, this@SimpleBottomSheetModal)
                }

                post {
                    if(layout.getEllipsisCount(1) > 0) TooltipCompat.setTooltipText(this, it.model.negativeButton.text)
                }
            }

            viewBinding.layoutSimpleBottomSheetModalButtonDividerLinearLayout.setBackgroundColor(it.model.appearance.dividerLinesColor)

            if(it.model.overflowMenu.buttons.isNotEmpty()) {
                viewBinding.layoutSimpleBottomSheetModalButtonDivider2ContainerConstraintLayout.visibility = View.VISIBLE
                viewBinding.layoutSimpleBottomSheetModalButtonDivider2LinearLayout.setBackgroundColor(it.model.appearance.dividerLinesColor)

                viewBinding.layoutSimpleBottomSheetModalMoreOptionsButtonContainerConstraintLayout.visibility = View.VISIBLE
                if(it.model.overflowMenu.overflowIconTintColor != null)
                    DrawableCompat.setTint(viewBinding.layoutSimpleBottomSheetModalMoreOptionsButtonIconImageView.drawable, it.model.overflowMenu.overflowIconTintColor!!)

                viewBinding.layoutSimpleBottomSheetModalMoreOptionsButtonContainerConstraintLayout.background =
                    DrawableBuilder()
                        .cornerRadius(1000000)
                        .ripple()
                        .rippleColor(GraphicsUtils.getRippleColor(getCurrentViewModel().model.appearance.backgroundColor))
                        .build()

                viewBinding.layoutSimpleBottomSheetModalMoreOptionsButtonContainerConstraintLayout.setOnClickListener {  }
            }
        }
    }

    override fun initializeAppearance() {
        getCurrentViewModel().model.appearance.run {
            viewBinding.layoutSimpleBottomSheetModalContentContainerConstraintLayout.background =
                DrawableBuilder()
                    .solidColor(backgroundColor)
                    .cornerRadii(cornerRadiusTopLeftPx, cornerRadiusTopRightPx, cornerRadiusBottomRightPx, cornerRadiusBottomLeftPx)
                    .strokeWidth(strokeWidth)
                    .strokeColor(strokeColor)
                    .ripple()
                    .rippleColor(GraphicsUtils.getRippleColor(backgroundColor))
                    .build()

            if(getCurrentViewModel().model.contextMenu.buttons.isNotEmpty()) {
                viewBinding.layoutSimpleBottomSheetModalTouchConstraintLayout.background =
                    DrawableBuilder()
                        .cornerRadii(cornerRadiusTopLeftPx, cornerRadiusTopRightPx, cornerRadiusBottomRightPx, cornerRadiusBottomLeftPx)
                        .ripple()
                        .rippleColor(GraphicsUtils.getRippleColor(getCurrentViewModel().model.appearance.backgroundColor))
                        .build()

                viewBinding.layoutSimpleBottomSheetModalTouchConstraintLayout.setOnClickListener {  }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun drawContent() {
        if(this::viewBinding.isInitialized)
            getCurrentViewModel().let {
                initializeAppearance()

                initializeList(
                    recyclerView = viewBinding.layoutSimpleBottomSheetModalTextContainerRecyclerView,
                    adapter = SimpleBottomSheetModalAdapter(
                        title = it.model.title,
                        textContentItems = mutableListOf<Text>().apply {
                            it.model.textContent.forEach { (_, text) ->
                                add(text)
                            }
                        },
                        hostActivity = requireActivity(),
                        touchHolder = viewBinding.layoutSimpleBottomSheetModalTouchConstraintLayout
                    ),
                    layoutManager = LinearLayoutManager(fragmentContext)
                )

                viewBinding.layoutSimpleBottomSheetModalTextContainerRecyclerView.layoutManager!!.onRestoreInstanceState(getCurrentViewModel().model.savedData.textContentListState)

                viewBinding.layoutSimpleBottomSheetModalTextContainerRecyclerView.run {
                    addOnScrollListener(
                        object : RecyclerView.OnScrollListener() {
                            override fun onScrollStateChanged(
                                recyclerView: RecyclerView,
                                newState: Int
                            ) {
                                getCurrentViewModel().model.savedData.textContentListState = layoutManager?.onSaveInstanceState()
                            }
                        }
                    )
                }

                initializeButtons()
            }
    }
    
    class Builder internal constructor(tag: String = BottomSheetModalsTagUtils.generateRandomTag(), restorePreviousStateIfSaved: Boolean = true) {
        var model =
            if(restorePreviousStateIfSaved && BottomSheetModalsService.getModalModelFromMap<SimpleBottomSheetModalModel>(tag) != null)
                BottomSheetModalsService.getModalModelFromMap(tag)!!
            else {
                BottomSheetModalsTagUtils.transientTagRegistry.add(tag)

                SimpleBottomSheetModalModel(
                    tag = tag,

                    appearance = Appearance(
                        backgroundColor = Color.WHITE,
                        50, 50, 50, 50,
                        strokeWidth = 1,
                        strokeColor = Color.DKGRAY,
                        dividerLinesColor = Color.BLACK
                    ),

                    title = Text(text = "Title", color = Color.BLACK),
                    textContent = mutableMapOf(),

                    positiveButton = Button(text = "Confirm", textColor = Color.BLACK),
                    negativeButton = Button(text = "Cancel", textColor = Color.BLACK),

                    contextMenu = ContextMenu(),
                    overflowMenu = OverflowMenu(),

                    callbacks = Callbacks()
                )
            }


        inline fun setAppearance(initialization: (oldAppearance: Appearance) -> Appearance) = this.apply {
            model.appearance = initialization.invoke(model.appearance)
        }

        inline fun setTitle(initialization: (oldTitle: Text) -> Text) = this.apply {
            initialization.invoke(model.title).let {
                model.title = it
            }
        }

        inline fun setTextContent(initialization: (oldTextsMap: MutableMap<String, Text>) -> MutableMap<String, Text>) = this.apply {
            initialization.invoke(model.textContent).let {
                model.textContent = it
            }
        }

        inline fun setPositiveButton(initialization: (oldButton: Button) -> Button) = this.apply {
            initialization.invoke(model.positiveButton).let {
                model.positiveButton = it
            }
        }

        inline fun setNegativeButton(initialization: (oldButton: Button) -> Button) = this.apply {
            initialization.invoke(model.negativeButton).let {
                model.negativeButton = it
            }
        }

        inline fun setContextMenu(initialization: (oldContextMenu: ContextMenu) -> ContextMenu) = this.apply {
           initialization.invoke(model.contextMenu).let {
               model.contextMenu = it
           }
        }

        inline fun setOverflowMenu(initialization: (oldOverflowMenu: OverflowMenu) -> OverflowMenu) = this.apply {
           initialization.invoke(model.overflowMenu).let {
               model.overflowMenu = it
           }
        }

        inline fun setCallbacks(initialization: (oldCallbacks: Callbacks) -> Callbacks) = this.apply {
           initialization.invoke(model.callbacks).let {
               model.callbacks = it
           }
        }

        fun show(fragmentManager: FragmentManager, addAsSecondOnTop: Boolean = false, removeModelFromMapOnModalDismiss: Boolean = true) {
            if(addAsSecondOnTop || !BottomSheetModalsService.modalCurrentlyShowing) {
                BottomSheetModalsService.addModalModelToMap(model.tag, model)
                SimpleBottomSheetModal().apply {
                    arguments = bundleOf(
                        BottomSheetModalsExtensionVocabulary.KEY_REMOVE_FROM_MAP_ON_DISMISS to removeModelFromMapOnModalDismiss
                    )
                }.show(fragmentManager, model.tag)

                BottomSheetModalsTagUtils.transientTagRegistry.remove(model.tag)
            }
        }
    }
}