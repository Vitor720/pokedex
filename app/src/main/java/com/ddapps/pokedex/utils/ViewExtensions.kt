package com.ddapps.pokedex.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.load(imagemUrl: String?) {
    val imageView = this
    val url = "$IMAGE_URL$imagemUrl.png"
    val requestOptions = Glide.with(imageView.context).load(url)
    requestOptions.into(this)
}

fun View.swapVisibility(){
    if(this.visibility == View.VISIBLE)
        this.visibility = View.GONE
    else if(this.visibility == View.GONE)
        this.visibility = View.VISIBLE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}