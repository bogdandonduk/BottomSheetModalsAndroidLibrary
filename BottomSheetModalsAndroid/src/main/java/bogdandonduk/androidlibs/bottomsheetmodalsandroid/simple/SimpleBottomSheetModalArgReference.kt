package bogdandonduk.androidlibs.bottomsheetmodalsandroid.simple

import android.os.Parcelable
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.parcel.Parcelize

@Parcelize
class SimpleBottomSheetModalArgReference(
    var backgroundColor: Int,
    var title: String,
    var text: String,
    var textColor: Int,
    var titleColor: Int,
    var positiveBtnText: String,
    var positiveBtnTextColor: Int,
    var positiveBtnClickAction: (view: View, modal: BottomSheetDialogFragment) -> Unit,
    var negativeBtnText: String,
    var negativeBtnTextColor: Int,
    var negativeBtnClickAction: (view: View, modal: BottomSheetDialogFragment) -> Unit
) : Parcelable