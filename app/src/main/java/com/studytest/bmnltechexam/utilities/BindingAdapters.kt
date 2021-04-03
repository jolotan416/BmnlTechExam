package com.studytest.bmnltechexam.utilities

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, imageUrl: String) {
        Glide.with(imageView)
            .load(imageUrl)
            .into(imageView)
    }

    @BindingAdapter("willShow")
    @JvmStatic
    fun setVisibility(view: View, willShow: Boolean) {
        view.visibility = if (willShow) View.VISIBLE else View.GONE
    }
}