package bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.anatomy.Text
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.databinding.LayoutSimpleBottomSheetModalTextItemBinding
import bogdandonduk.androidlibs.commonpreferencesutilsandroid.VibrationUtils
import bogdandonduk.androidlibs.viewbindingutilsandroid.ViewBinder

class SimpleBottomSheetModalAdapter(
    var title: Text,
    var textContentItems: MutableList<Text>,
    var hostActivity: FragmentActivity,
    var touchHolder: View
) : RecyclerView.Adapter<SimpleBottomSheetModalAdapter.SimpleBottomSheetModalTextViewHolder>() {
    lateinit var hostRecyclerView: RecyclerView

    init {
        textContentItems.add(0, title)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        hostRecyclerView = recyclerView
    }

    @SuppressLint("ClickableViewAccessibility", "InflateParams")
    inner class SimpleBottomSheetModalTextViewHolder(
        override val viewBindingInitialization: () -> LayoutSimpleBottomSheetModalTextItemBinding,
        override var viewBinding: LayoutSimpleBottomSheetModalTextItemBinding? = viewBindingInitialization.invoke()
    ) : RecyclerView.ViewHolder(viewBinding!!.root), ViewBinder<LayoutSimpleBottomSheetModalTextItemBinding> {

        init {
            getCurrentViewBinding().root.setOnTouchListener { v, event ->
                touchHolder.dispatchTouchEvent(event)

                false
            }

            getCurrentViewBinding().root.run {
                setOnLongClickListener {
                    if(textContentItems.isNotEmpty()) {
                        VibrationUtils.vibrateOneShot(hostActivity, 50)
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
        holder.getCurrentViewBinding().run {
            textContentItems[position].let {
                layoutSimpleBottomSheetModalItemIconContainerConstraintLayout.visibility = if(it.icon == null) View.GONE else View.VISIBLE
                layoutSimpleBottomSheetModalItemIconImageView.setImageDrawable(it.icon)

                layoutSimpleBottomSheetModalItemTextTextView.run {
                    textSize = if(position != 0) 16f else 24f
                    setTextColor(it.color)
                    setTypeface(null, if(position != 0) Typeface.NORMAL else Typeface.BOLD)

                    text = it.text
                }

                layoutSimpleBottomSheetModalItemUnderplaceholderLinearLayout.visibility = if(position != 0) View.GONE else View.VISIBLE
            }

        }
    }

    override fun getItemCount() = textContentItems.size
}