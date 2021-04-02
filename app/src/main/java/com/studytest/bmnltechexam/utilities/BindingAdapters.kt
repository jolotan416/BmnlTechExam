package com.studytest.bmnltechexam.utilities

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
}