package com.jeancorzo.rickandmorty.utils

import android.view.View
import android.view.ViewGroup

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible(visible: Boolean = true) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}
