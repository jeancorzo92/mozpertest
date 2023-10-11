package com.jeancorzo.rickandmorty.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.jeancorzo.rickandmorty.R


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    imageUrl.isNullOrBlank().let {
        view.load(imageUrl) {
            val pixels = view.context.resources.getDimensionPixelSize(R.dimen.recycler_item_image_corners).toFloat()
            this.transformations(RoundedCornersTransformation(pixels))
        }
    }
}

@BindingAdapter("drawableTint")
fun TextView.setDrawableTint(@ColorInt color: Int) {
    compoundDrawables.filterNotNull().forEach {
        it.setTint(color)
    }
}