package bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsExtensionVocabulary
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.BottomSheetModalsTagUtils
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.anatomy.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SimpleBottomSheetModalBuilder internal constructor(tag: String = BottomSheetModalsTagUtils.generateRandomTag(), restoreSavedStateIfSaved: Boolean = false) {
    @PublishedApi
    internal var model =
        if(restoreSavedStateIfSaved && BottomSheetModalsService.getModalModelFromMap<SimpleBottomSheetModalModel>(tag) != null) {
            val savedModel = BottomSheetModalsService.getModalModelFromMap<SimpleBottomSheetModalModel>(tag)!!

            SimpleBottomSheetModalModel(
                tag = savedModel.tag,

                appearance = Appearance(
                    backgroundColor = savedModel.appearance.backgroundColor,

                    strokeWidth = savedModel.appearance.strokeWidth,
                    strokeColor = savedModel.appearance.strokeColor,

                    dividerLinesColor = savedModel.appearance.dividerLinesColor,

                    cornerRadiusTopLeftPx = savedModel.appearance.cornerRadiusTopLeftPx,
                    cornerRadiusTopRightPx = savedModel.appearance.cornerRadiusTopRightPx,
                    cornerRadiusBottomRightPx = savedModel.appearance.cornerRadiusBottomRightPx,
                    cornerRadiusBottomLeftPx = savedModel.appearance.cornerRadiusBottomLeftPx,
                ),

                title = Text(
                    text = savedModel.title.text,
                    textColor = savedModel.title.textColor,
                    icon = savedModel.title.icon,
                    iconTintColor = savedModel.title.iconTintColor
                ),

                textContent = mutableMapOf<String, Text>().apply {
                    savedModel.textContent.forEach {
                        this[it.key] = it.value
                    }
                },

                positiveButton = Button(
                    text = savedModel.positiveButton.text,
                    textColor = savedModel.positiveButton.textColor,
                    icon = savedModel.positiveButton.icon,
                    iconTintColor = savedModel.positiveButton.iconTintColor,
                    clickAction = savedModel.positiveButton.clickAction
                ),

                negativeButton = Button(
                    text = savedModel.negativeButton.text,
                    textColor = savedModel.negativeButton.textColor,
                    icon = savedModel.negativeButton.icon,
                    iconTintColor = savedModel.negativeButton.iconTintColor,
                    clickAction = savedModel.negativeButton.clickAction
                ),

                contextMenu = ContextMenu(savedModel.contextMenu.buttons),
                overflowMenu = OverflowMenu(savedModel.overflowMenu.overflowIconTintColor, savedModel.overflowMenu.buttons),

                callbacks = Callbacks(
                    onCancelAction = savedModel.callbacks.onCancelAction,
                    onDismissAction = savedModel.callbacks.onDismissAction
                )
            )
        } else {
            BottomSheetModalsTagUtils.transientTagRegistry.add(tag)

            SimpleBottomSheetModalModel(
                tag = tag,

                appearance = Appearance(
                    backgroundColor = Color.WHITE,
                    strokeWidth = 1,
                    strokeColor = Color.DKGRAY,
                    dividerLinesColor = Color.BLACK,
                    50, 50, 50, 50,
                ),

                title = Text(text = "Title", textColor = Color.BLACK),
                textContent = mutableMapOf(Pair("0", Text(text = "Text", Color.BLACK))),

                positiveButton = Button(text = "Confirm", textColor = Color.BLACK),
                negativeButton = Button(text = "Cancel", textColor = Color.BLACK),

                contextMenu = ContextMenu(mutableMapOf()),
                overflowMenu = OverflowMenu(overflowIconTintColor = Color.BLACK, buttons = mutableMapOf()),

                callbacks = Callbacks()
            )
        }

    inline fun setBackgroundColor(modification: (oldColor: Int) -> Int) = this.apply {
        modification.invoke(model.appearance.backgroundColor).let {
            model.appearance.backgroundColor = it
        }
    }

    fun getBackgroundColor() = model.appearance.backgroundColor

    inline fun setStrokeWidth(modification: (oldWidth: Int) -> Int) = this.apply {
        modification.invoke(model.appearance.strokeWidth).let {
            model.appearance.strokeWidth = it
        }
    }

    fun getStrokeWidth() = model.appearance.strokeWidth

    inline fun setStrokeColor(modification: (oldColor: Int) -> Int) = this.apply {
        modification.invoke(model.appearance.strokeColor).let {
            model.appearance.strokeColor = it
        }
    }

    fun getStrokeColor() = model.appearance.strokeColor

    inline fun setDividerLinesColor(modification: (oldColor: Int) -> Int) = this.apply {
        modification.invoke(model.appearance.dividerLinesColor).let {
            model.appearance.dividerLinesColor = it
        }
    }

    fun getDividerLinesColor() = model.appearance.dividerLinesColor

    inline fun setCornerRadiusTopLeftPx(modification: (oldRadius: Int) -> Int) = this.apply {
        modification.invoke(model.appearance.cornerRadiusTopLeftPx).let {
            model.appearance.cornerRadiusTopLeftPx = it
        }
    }

    fun getCornerRadiusTopLeftPx() = model.appearance.cornerRadiusTopLeftPx

    inline fun setCornerRadiusTopRightPx(modification: (oldRadius: Int) -> Int) = this.apply {
        modification.invoke(model.appearance.cornerRadiusTopRightPx).let {
            model.appearance.cornerRadiusTopRightPx = it
        }
    }

    fun getCornerRadiusTopRightPx() = model.appearance.cornerRadiusTopRightPx

    inline fun setCornerRadiusBottomRightPx(modification: (oldRadius: Int) -> Int) = this.apply {
        modification.invoke(model.appearance.cornerRadiusBottomRightPx).let {
            model.appearance.cornerRadiusBottomRightPx = it
        }
    }

    fun getCornerRadiusBottomRightPx() = model.appearance.cornerRadiusBottomRightPx

    inline fun setCornerRadiusBottomLeftPx(modification: (oldRadius: Int) -> Int) = this.apply {
        modification.invoke(model.appearance.cornerRadiusBottomLeftPx).let {
            model.appearance.cornerRadiusBottomLeftPx = it
        }
    }

    fun getCornerRadiusBottomLeftPx() = model.appearance.cornerRadiusBottomLeftPx

    inline fun setTitleText(modification: (oldText: String) -> String) = this.apply {
        modification.invoke(model.title.text).let {
            model.title.text = it
        }
    }

    fun getTitleText() = model.title.text

    inline fun setTitleTextColor(modification: (oldColor: Int) -> Int) = this.apply {
        modification.invoke(model.title.textColor).let {
            model.title.textColor = it
        }
    }

    fun getTitleTextColor() = model.title.textColor

    fun addTextContentItem(item: Text, tag: String? = null) {
        model.textContent[tag ?: model.textContent.size.toString()] = item
    }

    fun deleteTextContentItem(tag: String) {
        model.textContent.remove(tag)
    }

    fun getTextContentItem(tag: String) = model.textContent[tag]

    fun getAllTextContentItems() = model.textContent

    inline fun setAllTextContentItems(modification: (oldItems: MutableMap<String, Text>) -> MutableMap<String, Text>) {
        modification.invoke(model.textContent).let {
            model.textContent = it
        }
    }

    inline fun setPositiveButtonText(modification: (oldText: String) -> String) = this.apply {
        modification.invoke(model.positiveButton.text).let {
            model.positiveButton.text = it
        }
    }

    fun getPositiveButtonText() = model.positiveButton.text

    inline fun setPositiveButtonTextColor(modification: (oldColor: Int) -> Int) = this.apply {
        modification.invoke(model.positiveButton.textColor).let {
            model.positiveButton.textColor = it
        }
    }

    fun getPositiveButtonTextColor() = model.positiveButton.textColor

    inline fun setPositiveButtonIcon(modification: (oldIcon: Drawable?) -> Drawable?) = this.apply {
        modification.invoke(model.positiveButton.icon).let {
            model.positiveButton.icon = it
        }
    }

    fun getPositiveButtonIcon() = model.positiveButton.icon

    inline fun setPositiveButtonIconTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        modification.invoke(model.positiveButton.iconTintColor).let {
            model.positiveButton.iconTintColor = it
        }
    }

    fun getPositiveButtonIconTintColor() = model.positiveButton.iconTintColor

    fun setPositiveButtonClickAction(modification: (oldAction: ((view: View, modal: BottomSheetDialogFragment) -> Unit)?) -> ((view: View, modal: BottomSheetDialogFragment) -> Unit)?) = this.apply {
        modification.invoke(model.positiveButton.clickAction).let {
            model.positiveButton.clickAction = it
        }
    }

    fun getPositiveButtonClickAction() = model.positiveButton.clickAction

    inline fun setNegativeButtonText(modification: (oldText: String) -> String) = this.apply {
        modification.invoke(model.negativeButton.text).let {
            model.negativeButton.text = it
        }
    }

    fun getNegativeButtonText() = model.negativeButton.text

    inline fun setNegativeButtonTextColor(modification: (oldColor: Int) -> Int) = this.apply {
        modification.invoke(model.negativeButton.textColor).let {
            model.negativeButton.textColor = it
        }
    }

    fun getNegativeButtonTextColor() = model.negativeButton.textColor

    inline fun setNegativeButtonIcon(modification: (oldIcon: Drawable?) -> Drawable?) = this.apply {
        modification.invoke(model.negativeButton.icon).let {
            model.negativeButton.icon = it
        }
    }

    fun getNegativeButtonIcon() = model.negativeButton.icon

    inline fun setNegativeButtonIconTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        modification.invoke(model.negativeButton.iconTintColor).let {
            model.negativeButton.iconTintColor = it
        }
    }

    fun getNegativeButtonIconTintColor() = model.negativeButton.iconTintColor

    fun setNegativeButtonClickAction(modification: (oldAction: ((view: View, modal: BottomSheetDialogFragment) -> Unit)?) -> ((view: View, modal: BottomSheetDialogFragment) -> Unit)?) = this.apply {
        modification.invoke(model.negativeButton.clickAction).let {
            model.negativeButton.clickAction = it
        }
    }

    fun getNegativeButtonClickAction() = model.negativeButton.clickAction

    fun setOnCancelAction(modification: (oldAction: ((view: View, modal: BottomSheetDialogFragment) -> Unit)?) -> ((view: View, modal: BottomSheetDialogFragment) -> Unit)?) = this.apply {
        modification.invoke(model.callbacks.onCancelAction).let {
            model.callbacks.onCancelAction = it
        }
    }

    fun getOnCancelAction() = model.callbacks.onCancelAction

    fun setOnDismissAction(modification: (oldAction: ((view: View, modal: BottomSheetDialogFragment) -> Unit)?) -> ((view: View, modal: BottomSheetDialogFragment) -> Unit)?) = this.apply {
        modification.invoke(model.callbacks.onDismissAction).let {
            model.callbacks.onDismissAction = it
        }
    }

    fun getOnDismissAction() = model.callbacks.onDismissAction

    fun getTag() = model.tag

    fun save() = this.apply {
        BottomSheetModalsService.addModalModelToMap(model.tag, model)
    }

    fun show(fragmentManager: FragmentManager, addAsSecondOnTop: Boolean = false, saveStateAfterDismissal: Boolean = false) : String? =
        if(addAsSecondOnTop || !BottomSheetModalsService.modalCurrentlyShowing) {
            save()

            SimpleBottomSheetModal().apply {
                arguments = bundleOf(
                    BottomSheetModalsExtensionVocabulary.KEY_REMOVE_FROM_MAP_ON_DISMISS to !saveStateAfterDismissal
                )
            }.show(fragmentManager, model.tag)

            BottomSheetModalsTagUtils.transientTagRegistry.remove(model.tag)

            model.tag
        } else null
}