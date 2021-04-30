package bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BaseBottomSheetModal
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.databinding.LayoutSimpleBottomSheetModalBinding
import bogdandonduk.androidlibs.viewbindingutilsandroid.FragmentViewBinder
import bogdandonduk.androidlibs.viewmodelutilsandroid.ViewModelHost

class SimpleBottomSheetModal : BaseBottomSheetModal(),
    FragmentViewBinder<LayoutSimpleBottomSheetModalBinding>, ViewModelHost<SimpleBottomSheetModalViewModel> {
    override lateinit var viewBinding: LayoutSimpleBottomSheetModalBinding

    override var viewModel: SimpleBottomSheetModalViewModel? = null
    override val viewModelInitialization: () -> SimpleBottomSheetModalViewModel = {
        val tag = requireArguments().getString(BottomSheetModalsService.KEY_ARGUMENT_TAG)!!

        ViewModelProvider(
            viewModelStore,
            SimpleBottomSheetModalViewModel.Factory(
                BottomSheetModalsService.getSimpleModalArgReferenceForTag(tag)!!,
                tag
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
                layoutSimpleBottomSheetModalContentConstraintLayout.background =
                    getBackgroundDrawable(backgroundColor = it.argReference.backgroundColor)

                layoutSimpleBottomSheetModalTitleTextView.setTextColor(it.argReference.titleColor)
                layoutSimpleBottomSheetModalTitleTextView.text = it.argReference.title

                layoutSimpleBottomSheetModalTextTextView.setTextColor(it.argReference.textColor)
                layoutSimpleBottomSheetModalTextTextView.text = it.argReference.text

                layoutSimpleBottomSheetModalPositiveBtnTextView.setTextColor(it.argReference.positiveBtnTextColor)
                layoutSimpleBottomSheetModalPositiveBtnTextView.text = it.argReference.positiveBtnText
                layoutSimpleBottomSheetModalPositiveBtnContainerConstraintLayout.setOnClickListener { view ->
                    it.argReference.positiveBtnClickAction.invoke(view, this@SimpleBottomSheetModal)
                }

                layoutSimpleBottomSheetModalNegativeBtnTextView.setTextColor(it.argReference.negativeBtnTextColor)
                layoutSimpleBottomSheetModalNegativeBtnTextView.text = it.argReference.negativeBtnText
                layoutSimpleBottomSheetModalNegativeBtnContainerConstraintLayout.setOnClickListener { view ->
                    it.argReference.negativeBtnClickAction.invoke(view, this@SimpleBottomSheetModal)
                }
            }
        }
    }
}