package com.shaadi.assignment.utils

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.shaadi.assignment.R
import com.shaadi.assignment.data.local.db.typeconverters.InvitationStatus


@BindingAdapter("app:imageUrl")
fun setImage(view: ImageView, url: String?) {
    url?.let {
        Glide.with(view.context)
            .load(it)
            .circleCrop()
            .into(view)
    }
}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("app:textStatus")
fun setTextStatus(textView: TextView, invitationStatus: InvitationStatus) {

    if (textView.visibility == View.VISIBLE) {

        val status = if (invitationStatus == InvitationStatus.ACCEPTED) "Accepted" else "Rejected"
        val color = if (invitationStatus == InvitationStatus.ACCEPTED) Color.GREEN else Color.RED

        val finalString: String =
            textView.context.getString(R.string.invitation_status_message, status)

        val spannableStringBuilder = SpannableStringBuilder(finalString)
        val startIndex: Int = finalString.indexOf(status)

        spannableStringBuilder.setSpan(ForegroundColorSpan(color), startIndex, startIndex + status.length, 0);

        textView.setText(spannableStringBuilder, TextView.BufferType.SPANNABLE)

    }

}