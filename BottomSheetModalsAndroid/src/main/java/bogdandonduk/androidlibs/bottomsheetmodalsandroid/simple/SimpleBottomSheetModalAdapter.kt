package bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.anatomy.Text
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.databinding.LayoutSimpleBottomSheetModalTextItemBinding
import bogdandonduk.androidlibs.commonpreferencesutilsandroid.VibrationUtils
import bogdandonduk.androidlibs.viewbindingutilsandroid.ViewBinder

internal class SimpleBottomSheetModalAdapter(
    var title: Text,
    var textContentItems: MutableList<Text>,
    var hostActivity: FragmentActivity,
    var touchHolder: View
) : RecyclerView.Adapter<SimpleBottomSheetModalAdapter.SimpleBottomSheetModalTextViewHolder>() {
    private lateinit var hostRecyclerView: RecyclerView

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
            getCurrentViewBinding().root.run {
                setOnTouchListener { _, event ->
                    touchHolder.dispatchTouchEvent(event)

                    false
                }

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
                if(it.icon != null) {
                    layoutSimpleBottomSheetModalItemIconContainerConstraintLayout.visibility = View.VISIBLE

                    layoutSimpleBottomSheetModalItemIconImageView.run {
                        layoutParams = if(position == 0)
                            ConstraintLayout.LayoutParams(80, 80)
                        else
                            ConstraintLayout.LayoutParams(60, 60)

                        setImageDrawable(it.icon)
                    }
                } else
                    layoutSimpleBottomSheetModalItemIconContainerConstraintLayout.visibility = View.GONE

                layoutSimpleBottomSheetModalItemTextTextView.run {
                    textSize = if(position == 0) 20f else 16f
                    setTextColor(it.textColor)
                    setTypeface(null, if(position == 0) Typeface.BOLD else Typeface.NORMAL)

                    text = it.text
                }

                layoutSimpleBottomSheetModalItemUnderplaceholderLinearLayout.visibility = if(position == 0) View.VISIBLE else View.GONE
            }
        }
    }

    override fun getItemCount() = textContentItems.size
}