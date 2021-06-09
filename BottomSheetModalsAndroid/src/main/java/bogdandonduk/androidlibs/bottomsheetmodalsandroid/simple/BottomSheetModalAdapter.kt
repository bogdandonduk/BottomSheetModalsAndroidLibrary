package bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.core.anatomy.Text
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.databinding.LayoutBottomSheetModalTextItemBinding
import bogdandonduk.androidlibs.commonpreferencesutilsandroid.VibrationUtils
import bogdandonduk.androidlibs.viewbindingutilsandroid.ViewBinder

internal class BottomSheetModalAdapter(
    var title: Text,
    var textContentItems: MutableList<Text>,
    var hostActivity: FragmentActivity,
    var touchHolder: View
) : RecyclerView.Adapter<BottomSheetModalAdapter.ViewHolder>() {
    private lateinit var hostRecyclerView: RecyclerView

    init {
        textContentItems.add(0, title)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        hostRecyclerView = recyclerView
    }

    @SuppressLint("ClickableViewAccessibility", "InflateParams")
    inner class ViewHolder(
        override val viewBindingInitialization: () -> LayoutBottomSheetModalTextItemBinding,
        override var viewBinding: LayoutBottomSheetModalTextItemBinding? = viewBindingInitialization.invoke()
    ) : RecyclerView.ViewHolder(viewBinding!!.root), ViewBinder<LayoutBottomSheetModalTextItemBinding> {

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
    ) = ViewHolder({ LayoutBottomSheetModalTextItemBinding.inflate(hostActivity.layoutInflater, parent, false) })

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.getCurrentViewBinding().run {
            textContentItems[position].let {
                if(it.icon != null) {
                    layoutBottomSheetModalItemIconContainerConstraintLayout.visibility = View.VISIBLE

                    layoutBottomSheetModalItemIconImageView.run {
                        layoutParams = if(position == 0)
                            ConstraintLayout.LayoutParams(80, 80)
                        else
                            ConstraintLayout.LayoutParams(60, 60)

                        setImageDrawable(it.icon)
                    }
                } else
                    layoutBottomSheetModalItemIconContainerConstraintLayout.visibility = View.GONE

                layoutBottomSheetModalItemTextTextView.run {
                    textSize = if(position == 0) 20f else 16f
                    setTextColor(it.textColor)
                    setTypeface(null, if(position == 0) Typeface.BOLD else Typeface.NORMAL)

                    text = it.text
                }

                layoutBottomSheetModalItemUnderplaceholderLinearLayout.visibility = if(position == 0) View.VISIBLE else View.GONE
            }
        }
    }

    override fun getItemCount() = textContentItems.size
}