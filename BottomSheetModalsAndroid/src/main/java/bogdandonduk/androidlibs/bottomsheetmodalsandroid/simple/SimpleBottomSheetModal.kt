package bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.TooltipCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsExtensionVocabulary
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.anatomy.*
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base.BaseBottomSheetModal
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.compose.ButtonHostModal
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.databinding.LayoutSimpleBottomSheetModalBinding
import bogdandonduk.androidlibs.commonpreferencesutilsandroid.GraphicsUtils
import bogdandonduk.androidlibs.recyclerviewutilsandroid.RecyclerViewHost
import bogdandonduk.androidlibs.viewbindingutilsandroid.LateinitViewBinder
import bogdandonduk.androidlibs.viewmodelutilsandroid.GenericViewModelFactory
import bogdandonduk.androidlibs.viewmodelutilsandroid.ViewModelHost
import top.defaults.drawabletoolbox.DrawableBuilder

internal class SimpleBottomSheetModal : BaseBottomSheetModal(), ViewModelHost<SimpleBottomSheetModalViewModel>,
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

        getCurrentViewModel().model.callbacks.onCancelAction?.invoke(viewBinding.root, this)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        getCurrentViewModel().model.callbacks.onDismissAction?.invoke(viewBinding.root, this)
    }

    override fun initializeButtons() {
        getCurrentViewModel().let {
            viewBinding.layoutSimpleBottomSheetModalPositiveButtonTextView.run {
                background =
                    DrawableBuilder()
                        .cornerRadius(1000000)
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
                        .cornerRadius(1000000)
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

    private fun initializeTextContent() {
        initializeList(
            recyclerView = viewBinding.layoutSimpleBottomSheetModalTextContainerRecyclerView,
            adapter = SimpleBottomSheetModalAdapter(
                title = getCurrentViewModel().model.title,
                textContentItems = mutableListOf<Text>().apply {
                    getCurrentViewModel().model.textContent.forEach { (_, text) ->
                        add(text)
                    }
                },
                hostActivity = requireActivity(),
                touchHolder = viewBinding.layoutSimpleBottomSheetModalTouchConstraintLayout
            ),
            layoutManager = LinearLayoutManager(fragmentContext)
        )

        viewBinding.layoutSimpleBottomSheetModalTextContainerRecyclerView.run {
            layoutManager!!.onRestoreInstanceState(getCurrentViewModel().model.savedData.textContentListState)

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

                initializeTextContent()

                initializeButtons()
            }
    }
}