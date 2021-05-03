package bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BaseBottomSheetModal
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.databinding.LayoutSimpleBottomSheetModalBinding
import bogdandonduk.androidlibs.viewbindingutilsandroid.FragmentViewBinder
import bogdandonduk.androidlibs.viewmodelutilsandroid.ViewModelHost
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SimpleBottomSheetModal : BaseBottomSheetModal(),
    FragmentViewBinder<LayoutSimpleBottomSheetModalBinding>, ViewModelHost<SimpleBottomSheetModalViewModel> {
    override lateinit var viewBinding: LayoutSimpleBottomSheetModalBinding

    override var viewModel: SimpleBottomSheetModalViewModel? = null
    override val viewModelInitialization: () -> SimpleBottomSheetModalViewModel = {
        ViewModelProvider(
            viewModelStore,
            SimpleBottomSheetModalViewModel.Factory(
                BottomSheetModalsService.getSimpleModalArgReferenceForTag(tag!!)!!,
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

                layoutSimpleBottomSheetModalPositiveBtnTextView.setTextColor(it.argReference.positiveButtonTextColor)
                layoutSimpleBottomSheetModalPositiveBtnTextView.text = it.argReference.positiveButtonText
                layoutSimpleBottomSheetModalPositiveBtnContainerConstraintLayout.setOnClickListener { view ->
                    it.argReference.positiveButtonClickAction.invoke(view, this@SimpleBottomSheetModal)
                }

                layoutSimpleBottomSheetModalNegativeBtnTextView.setTextColor(it.argReference.negativeButtonTextColor)
                layoutSimpleBottomSheetModalNegativeBtnTextView.text = it.argReference.negativeButtonText
                layoutSimpleBottomSheetModalNegativeBtnContainerConstraintLayout.setOnClickListener { view ->
                    it.argReference.negativeButtonClickAction.invoke(view, this@SimpleBottomSheetModal)
                }
            }
        }
    }

    class Builder(var tag: String) {
        private var argReference = SimpleBottomSheetModalArgReference()

        fun setBackgroundColor(color: Int) : Builder = this.apply { argReference.backgroundColor = color }

        fun setTitle(title: String) : Builder = this.apply { argReference.title = title }

        fun setTitleColor(color: Int) : Builder = this.apply { argReference.titleColor = color }

        fun setText(text: String) : Builder = this.apply { argReference.text = text }

        fun setTextColor(color: Int) : Builder = this.apply { argReference.textColor = color }

        fun setPositiveButtonText(text: String) : Builder = this.apply { argReference.positiveButtonText = text }

        fun setPositiveButtonTextColor(color: Int) : Builder = this.apply { argReference.positiveButtonTextColor = color }

        fun setPositiveButtonClickAction(action: (view: View, modal: BottomSheetDialogFragment) -> Unit) : Builder = this.apply { argReference.positiveButtonClickAction = action }

        fun setNegativeButtonText(text: String) : Builder = this.apply { argReference.negativeButtonText = text }

        fun setNegativeButtonTextColor(color: Int) : Builder = this.apply { argReference.negativeButtonTextColor = color }

        fun setNegativeButtonClickAction(action: (view: View, modal: BottomSheetDialogFragment) -> Unit) : Builder = this.apply { argReference.negativeButtonClickAction = action }

        fun setOnCancelAction(action: (modal: DialogInterface) -> Unit) : Builder = this.apply { argReference.onCancelAction = action }

        fun setOnDismissAction(action: (modal: DialogInterface) -> Unit) : Builder = this.apply { argReference.onDismissAction = action }

        fun show(fragmentManager: FragmentManager) {
            if(!BottomSheetModalsService.modalShowingCurrently) {
                if(!BottomSheetModalsService.simpleModalsArgReferencesMap.containsKey(tag))
                    BottomSheetModalsService.simpleModalsArgReferencesMap[tag] = argReference

                SimpleBottomSheetModal().show(fragmentManager, tag)
            }
        }
    }
}