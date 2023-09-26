package com.jeancorzo.rickandmorty.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load


@BindingAdapter("app:imageUrl")
fun loadImage(view: ImageView, imageUrl: String) {
    view.load(imageUrl)
}