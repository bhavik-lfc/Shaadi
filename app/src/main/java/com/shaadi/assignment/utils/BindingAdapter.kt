package com.shaadi.assignment.utils

import android.graphics.Color
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
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
        val spannableStringBuilder = SpannableStringBuilder()

        val status = if (invitationStatus == InvitationStatus.ACCEPTED) "Accepted" else "Rejected"
        val color = if (invitationStatus == InvitationStatus.ACCEPTED) Color.GREEN else Color.RED
        val spannableString = SpannableString(status)
        spannableString.setSpan(ForegroundColorSpan(color), 0, status.length, 0)

        spannableStringBuilder.append("You have ")
        spannableStringBuilder.append(spannableString)
        spannableStringBuilder.append(" this user")

        textView.setText(spannableStringBuilder, TextView.BufferType.SPANNABLE)

    }

}