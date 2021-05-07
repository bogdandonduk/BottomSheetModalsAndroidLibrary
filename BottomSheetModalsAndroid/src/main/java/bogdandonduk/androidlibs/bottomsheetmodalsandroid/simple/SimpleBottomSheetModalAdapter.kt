package bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple

import android.graphics.Typeface
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.R
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.anatomy.TextItem
import bogdandonduk.androidlibs.bottomsheetmodalsandroid.databinding.LayoutSimpleBottomSheetModalTextItemBinding
import bogdandonduk.androidlibs.glideutilsandroid.GlideUtils
import bogdandonduk.androidlibs.viewbindingutilsandroid.ViewBinder

class SimpleBottomSheetModalAdapter(
    var title: TextItem,
    var textItems: MutableList<TextItem>,
    var hostActivity: FragmentActivity) : RecyclerView.Adapter<SimpleBottomSheetModalAdapter.SimpleBottomSheetModalTextViewHolder>() {

    class SimpleBottomSheetModalTextViewHolder(
        override val viewBindingInitialization: () -> LayoutSimpleBottomSheetModalTextItemBinding,
        override var viewBinding: LayoutSimpleBottomSheetModalTextItemBinding? = viewBindingInitialization.invoke()
    ) : RecyclerView.ViewHolder(viewBinding!!.root), ViewBinder<LayoutSimpleBottomSheetModalTextItemBinding>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = SimpleBottomSheetModalTextViewHolder(viewBindingInitialization = { LayoutSimpleBottomSheetModalTextItemBinding.inflate(hostActivity.layoutInflater, parent, false) })

    override fun onBindViewHolder(holder: SimpleBottomSheetModalTextViewHolder, position: Int) {
        Log.d("TAG", "onBindViewHolder: $position")

        when(position) {
            0 -> {
                holder.getCurrentViewBinding().layoutSimpleBottomSheetModalItemTextTextView.let {
                    with(title) {
                        it.textSize = 20f
                        it.setTextColor(textColor)
                        it.setTypeface(it.typeface, Typeface.BOLD)

                        it.text = text
                    }
                }
            }
            else -> {
                holder.getCurrentViewBinding().let {
                    with(textItems[position - 1]) {
                        if(icon == null) {
                            it.layoutSimpleBottomSheetModalItemIconContainerLinearLayout.visibility = View.GONE
                            it.layoutSimpleBottomSheetModalItemIconImageView.setImageDrawable(null)
                        } else {
                            it.layoutSimpleBottomSheetModalItemIconContainerLinearLayout.visibility = View.VISIBLE

                            GlideUtils.getInstance(hostActivity).bitmapRequestBuilder.load(R.drawable.ic_baseline_content_copy_24).into(it.layoutSimpleBottomSheetModalItemIconImageView)
                        }

                        it.layoutSimpleBottomSheetModalItemTextTextView.let {
                            it.textSize = 16f
                            it.setTextColor(textColor)
                            it.setTypeface(it.typeface, Typeface.NORMAL)

                            it.text = text
                        }
                    }
                }
            }
        }

    }

    override fun getItemCount() = textItems.size + 1
}