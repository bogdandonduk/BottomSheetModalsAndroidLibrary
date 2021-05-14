package bogdandonduk.androidlibs.bottomsheetmodalsandroid.anatomy

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.BottomSheetModalsService
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.R
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.databinding.LayoutOverflowPopupWindowTextItemBinding
import bogdandonduk.androidlibs.viewbindingutilsandroid.ViewBinder
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import top.defaults.drawabletoolbox.DrawableBuilder

object BottomSheetModalAnatomy {

    class TextItem(var text: String, @ColorInt var textColor: Int, var icon: Drawable? = null)

    class ButtonItem(var text: String, @ColorInt var textColor: Int, var clickAction: (view: View, modal: BottomSheetDialogFragment) -> Unit)

    object Popup {
        open class Item(
            open var text: String,
            @ColorInt open var textColor: Int,
            open var icon: Icon? = null,
            open var clickAction: (view: View, popup: PopupWindow) -> Unit
        ) {
            class Icon(
                var context: Context,
                var icon: Drawable? = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_content_copy_24, null),
                @ColorInt var iconTintColor: Int = Color.DKGRAY
            )
        }

        abstract class BaseMenu internal constructor() {
            var items: MutableList<Item> = mutableListOf()

            @ColorInt var backgroundColor: Int = Color.WHITE
            @ColorInt var strokeColor: Int = Color.DKGRAY
            var cornerRadiusPx: Int = 30

            var onCancelListener: ((popup: PopupWindow) -> Unit)? = null
            var onDismissListener: ((popup: PopupWindow) -> Unit)? = null
        }

        abstract class BaseMenuBuilder<T : BaseMenu> internal constructor(var menu: T) {
            fun addItems(items: MutableList<Item>, override: Boolean = false) = this.apply {
                menu.items.apply {
                    if(override) clear()

                    addAll(items)
                }
            }

            fun setBackgroundColor(@ColorInt color: Int) = this.apply { menu.backgroundColor = color }

            fun setStrokeColor(@ColorInt color: Int) = this.apply { menu.strokeColor = color }

            fun setCornerRadiusPx(radiusPx: Int) = this.apply { menu.cornerRadiusPx = radiusPx }

            fun setOnDismissListener(action: (popup: PopupWindow) -> Unit) = this.apply { menu.onDismissListener = action }

            fun setOnCancelListener(action: (popup: PopupWindow) -> Unit) = this.apply { menu.onCancelListener = action }

            fun build() = menu
        }

        object ModalContext {
            fun getMenuBuilder() = MenuBuilder()

            class MenuBuilder internal constructor() : BaseMenuBuilder<Menu>(Menu())

            class Menu : BaseMenu()

            object DefaultItems {
                class ItemCopy(
                    var context: Context,
                    override var text: String,
                    @ColorInt override var textColor: Int,
                    var textItems: MutableList<TextItem>,
                    override var icon: Icon? = Icon(context, ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_content_copy_24, null), textColor),
                    var toast: Toast? = null,
                    var postAction: ((copiedText: String) -> Unit)? = null
                ) : Item(text, textColor, icon, { _: View, popup: PopupWindow ->
                    popup.dismiss()

                    toast?.apply {
                        BottomSheetModalsService.handler.postDelayed(
                            {
                                cancel()
                            },
                            1500
                        )
                    }?.show()

                    val appendedText = StringBuilder().apply {
                        textItems.forEach {
                            append(" ${it.text}")
                        }
                    }.toString()

                    (context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(
                        ClipData.newPlainText(
                            "dialog_text",
                            appendedText
                        )
                    )

                    postAction?.invoke(appendedText)

                    Unit
                })
            }
        }

        object OverflowButtonsContext {
            fun getMenuBuilder() : MenuBuilder = MenuBuilder()

            class MenuBuilder internal constructor() : BaseMenuBuilder<Menu>(Menu()) {

                fun setOverflowButtonTooltipText(text: String) = (this as MenuBuilder).apply { menu.overflowButtonTooltipText = text }

                fun setOverflowIconTintColor(@ColorInt color: Int) = this.apply { menu.overflowIconTintColor = color }
            }

            class Menu : BaseMenu() {
                var overflowButtonTooltipText: String? = null
                @ColorInt var overflowIconTintColor: Int = Color.DKGRAY
            }
        }

        class Adapter(
            private val items: MutableList<Item>,
            var hostActivity: FragmentActivity,
            var hostPopupWindow: PopupWindow
        ) : RecyclerView.Adapter<Adapter.ViewHolder>() {
            lateinit var hostRecyclerView: RecyclerView

            override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
                hostRecyclerView = recyclerView
            }

            inner class ViewHolder(
                override val viewBindingInitialization: () -> LayoutOverflowPopupWindowTextItemBinding,
                override var viewBinding: LayoutOverflowPopupWindowTextItemBinding? = viewBindingInitialization.invoke()
            ) : RecyclerView.ViewHolder(viewBinding!!.root),
                ViewBinder<LayoutOverflowPopupWindowTextItemBinding> {
                lateinit var item: Item

                init {
                    getCurrentViewBinding().root.run {
                        setOnClickListener {
                            if(this@ViewHolder::item.isInitialized) item.clickAction.invoke(it, hostPopupWindow)
                        }
                    }
                }
            }

            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ) = ViewHolder(viewBindingInitialization = {
                LayoutOverflowPopupWindowTextItemBinding.inflate(
                    hostActivity.layoutInflater,
                    parent,
                    false
                )
            })

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                holder.run {
                    item = items[position]

                    getCurrentViewBinding().run {
                        layoutOverflowPopupWindowItemIconImageView.run {
                            if(item.icon != null) {
                                visibility = View.VISIBLE

                                setImageDrawable(item.icon!!.icon)
                            } else {
                                visibility = View.GONE

                                setImageDrawable(null)
                            }
                        }

                        layoutOverflowPopupWindowItemTextTextView.run {
                            setTextColor(item.textColor)

                            text = item.text
                        }
                    }
                }
            }

            override fun getItemCount() = items.size
        }
    }

    internal object Background {
        fun getModalBackgroundDrawable(backgroundColor: Int) =
            DrawableBuilder()
                .rectangle()
                .cornerRadius(50)
                .solidColor(backgroundColor)
                .strokeColor(Color.DKGRAY)
                .strokeWidth(1)
                .build()

        fun getPopupBackgroundDrawable(@ColorInt backgroundColor: Int, @ColorInt strokeColor: Int = Color.DKGRAY, cornerRadiusPx: Int = 30) =
            DrawableBuilder()
                .rectangle()
                .cornerRadius(30)
                .solidColor(backgroundColor)
                .strokeColor(Color.DKGRAY)
                .strokeWidth(1)
                .build()
    }
}