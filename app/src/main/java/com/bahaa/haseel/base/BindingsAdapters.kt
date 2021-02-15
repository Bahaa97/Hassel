package com.bahaa.haseel.base

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.Html
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.suke.widget.SwitchButton
import java.util.*

@BindingAdapter("app:cardBg")
fun cardBg(cardView: CardView, color: String?) {
    color?: return
    cardView.setCardBackgroundColor(Color.parseColor(color))
}

@BindingAdapter("app:errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
    view.error = errorMessage
}

@BindingAdapter("app:errorText")
fun setErrorMessage(view: EditText, errorMessage: String?) {
    view.error = errorMessage
}

@BindingAdapter("app:rateValue")
fun setRating(ratingBar: RatingBar, mVoteAverage: Double) {
    mVoteAverage
    ratingBar.rating = mVoteAverage.toFloat()
}

@BindingAdapter("app:setTextColor")
fun setTextColor(textView: TextView, color: String?) {
    color ?: return
    textView.setTextColor(Color.parseColor(color))
}

@BindingAdapter("app:setBackGroundColor")
fun setTextColor(view: View, color: String?) {
    color ?: return
    view.setBackgroundColor(Color.parseColor(color))
}

@BindingAdapter("app:setunderLine")
fun setUnderLine(textView: TextView, set: Boolean) {
    textView.setPaintFlags(textView.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
}

@BindingAdapter("app:currency", "app:amount")
fun setCurrencyAndAmount(
    textView: TextView,
    currency: String,
    amount: Double
) {
    textView.text = String.format(Locale.US, "%.2f", amount) + " " + currency
}

@BindingAdapter("app:htmlText")
fun setHtmlText(
    textView: TextView,
    text: String
) {
    textView.text = Html.fromHtml(text)
}

@BindingAdapter("colorForText")
fun colorForText(textView: TextView, color: String?) {

    color ?: return
    textView.setTextColor(Color.parseColor(color))
}

@BindingAdapter("app:bgForCard")
fun cardBackgroundColor(textView: CardView, color: String?) {
    color ?: return
    textView.setCardBackgroundColor(Color.parseColor(color))
}

@BindingAdapter("app:setChecked")
fun cardBackgroundColor(switchButton: SwitchButton, status: Int?) {
    status ?: return
    switchButton.isChecked = status != 0
}


//@BindingAdapter(value = ["loadImg", "placeholder"], requireAll = false)
//fun loadImg(imageView: ImageView, url: String?, placeHolder: Drawable?) {
//    if (!TextUtils.isEmpty(url)) {
//        var placeHolderId = placeHolder
//
//        if (placeHolderId == null) {
//            placeHolderId =
//                imageView.context.resources.getDrawable(R.drawable.ic_placeholder_square)
//        }
//
//        Picasso.get()
//            .load(url)
//            .error(placeHolderId!!)
//            .placeholder(placeHolderId)
//            .into(imageView)
//    }
//}


@BindingAdapter("android:src")
fun setImageUri(view: ImageView, imageUri: String?) {
    if (imageUri == null) {
        view.setImageURI(null)
    } else {
        view.setImageURI(Uri.parse(imageUri))
    }
}

@BindingAdapter("android:src")
fun setImageUri(view: ImageView, imageUri: Uri?) {
    view.setImageURI(imageUri)
}

@BindingAdapter("android:src")
fun setImageDrawable(view: ImageView, drawable: Drawable?) {
    view.setImageDrawable(drawable)
}

@BindingAdapter("android:src")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}
@BindingAdapter("android:text")
fun setImageResource(textView: TextView, resource: Int) {
    textView.setText(resource)
}
