package bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsExtensionVocabulary
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.anatomy.*
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.base.BaseBottomSheetModal
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.compose.ButtonHostModal
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.databinding.LayoutBottomSheetModalBinding
import bogdandonduk.androidlibs.popupsandroid.PopupService
import bogdandonduk.androidlibs.recyclerviewutilsandroid.RecyclerViewHost
import bogdandonduk.androidlibs.viewbindingutilsandroid.LateinitViewBinder
import bogdandonduk.androidlibs.viewmodelutilsandroid.GenericViewModelFactory
import bogdandonduk.androidlibs.viewmodelutilsandroid.ViewModelHost
import top.defaults.drawabletoolbox.DrawableBuilder

internal class BottomSheetModal : BaseBottomSheetModal(), ViewModelHost<BottomSheetModalViewModel>,
    LateinitViewBinder<LayoutBottomSheetModalBinding>, RecyclerViewHost, ButtonHostModal {
    override lateinit var viewBinding: LayoutBottomSheetModalBinding

    override var viewModel: BottomSheetModalViewModel? = null

    override val viewModelInitialization: () -> BottomSheetModalViewModel = {
        ViewModelProvider(
            viewModelStore,
            GenericViewModelFactory {
                BottomSheetModalViewModel(BottomSheetModalsService.getModalModel(tag!!)!!)
            }
        ).get(BottomSheetModalViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getCurrentViewModel().run {
            model.modal = this@BottomSheetModal
            removeModelFromMapOnModalDismiss = requireArguments().getBoolean(BottomSheetModalsExtensionVocabulary.KEY_REMOVE_FROM_MAP_ON_DISMISS, true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = LayoutBottomSheetModalBinding.inflate(inflater, container, false)

        return viewBinding.root
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)

        getCurrentViewModel().model.callbacks.onCancelAction?.invoke(this)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        getCurrentViewModel().model.callbacks.onDismissAction?.invoke(this)
    }

    override fun initializeButtons() {
        viewBinding.layoutBottomSheetModalPositiveButtonTextView.run {
            background =
                DrawableBuilder()
                    .cornerRadius(1000000)
                    .ripple()
                    .rippleColor(Color.DKGRAY)
                    .build()

            setTextColor(getCurrentViewModel().model.positiveButton.textColor)

            text = getCurrentViewModel().model.positiveButton.text

            setOnClickListener { view ->
                getCurrentViewModel().model.positiveButton.onClickAction?.invoke(view, this@BottomSheetModal)
            }

            post {
                if(layout.getEllipsisCount(1) > 0) setOnLongClickListener { view ->
                    PopupService.buildPopup("positive_button_tooltip").showAsTooltip(requireActivity(), view.id)

                    true
                }
            }
        }

        viewBinding.layoutBottomSheetModalNegativeButtonTextView.run {
            background =
                DrawableBuilder()
                    .cornerRadius(1000000)
                    .ripple()
                    .rippleColor(Color.DKGRAY)
                    .build()

            setTextColor(getCurrentViewModel().model.negativeButton.textColor)

            text = getCurrentViewModel().model.negativeButton.text

            setOnClickListener { view ->
                getCurrentViewModel().model.negativeButton.onClickAction?.invoke(view, this@BottomSheetModal)
            }

            post {
                if(layout.getEllipsisCount(1) > 0) setOnLongClickListener { view ->
                    PopupService.buildPopup("negative_button_tooltip").showAsTooltip(requireActivity(), view.id)

                    true
                }
            }
        }

        viewBinding.layoutBottomSheetModalButtonDividerLinearLayout.setBackgroundColor(getCurrentViewModel().model.appearance.dividerLinesColor)

        if(getCurrentViewModel().model.overflowMenu.buttons.isNotEmpty()) {
            viewBinding.layoutBottomSheetModalButtonDivider2ContainerConstraintLayout.visibility = View.VISIBLE
            viewBinding.layoutBottomSheetModalButtonDivider2LinearLayout.setBackgroundColor(getCurrentViewModel().model.appearance.dividerLinesColor)
            viewBinding.layoutBottomSheetModalMoreOptionsButtonContainerConstraintLayout.visibility = View.VISIBLE

            if(getCurrentViewModel().model.overflowMenu.overflowIconTintColor != null)
                DrawableCompat.setTint(viewBinding.layoutBottomSheetModalMoreOptionsButtonIconImageView.drawable, getCurrentViewModel().model.overflowMenu.overflowIconTintColor!!)

            viewBinding.layoutBottomSheetModalMoreOptionsButtonContainerConstraintLayout.background =
                DrawableBuilder()
                    .cornerRadius(1000000)
                    .ripple()
                    .rippleColor(Color.DKGRAY)
                    .build()

            viewBinding.layoutBottomSheetModalMoreOptionsButtonContainerConstraintLayout.setOnClickListener {  }
        }
    }

    private fun initializeTextContent() {
        initializeList(
            recyclerView = viewBinding.layoutBottomSheetModalTextContainerRecyclerView,
            adapter = BottomSheetModalAdapter(
                title = getCurrentViewModel().model.title,
                textContentItems = mutableListOf<Text>().apply {
                    getCurrentViewModel().model.textContent.forEach { (_, text) ->
                        add(text)
                    }
                },
                hostActivity = requireActivity(),
                touchHolder = viewBinding.layoutBottomSheetModalTouchConstraintLayout
            ),
            layoutManager = LinearLayoutManager(fragmentContext)
        )

        viewBinding.layoutBottomSheetModalTextContainerRecyclerView.run {
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
            viewBinding.layoutBottomSheetModalContentContainerConstraintLayout.background =
                DrawableBuilder()
                    .solidColor(backgroundColor)
                    .cornerRadii(cornerRadiusTopLeftPx, cornerRadiusTopRightPx, cornerRadiusBottomRightPx, cornerRadiusBottomLeftPx)
                    .strokeWidth(strokeWidth)
                    .strokeColor(strokeColor)
                    .ripple()
                    .rippleColor(Color.DKGRAY)
                    .build()

            if(getCurrentViewModel().model.contextMenu.buttons.isNotEmpty()) {
                viewBinding.layoutBottomSheetModalTouchConstraintLayout.background =
                    DrawableBuilder()
                        .cornerRadii(cornerRadiusTopLeftPx, cornerRadiusTopRightPx, cornerRadiusBottomRightPx, cornerRadiusBottomLeftPx)
                        .ripple()
                        .rippleColor(Color.DKGRAY)
                        .build()

                viewBinding.layoutBottomSheetModalTouchConstraintLayout.setOnClickListener {  }
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