package com.ddapps.pokedex.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun ImageView.load(imagemUrl: String?) {
    val imageView = this
    val url = if (imagemUrl!!.contains(".png")){
        imagemUrl

    }else{
        "$IMAGE_URL$imagemUrl.png"
    }
    val requestOptions = Glide.with(imageView.context).load(url).apply(
            RequestOptions().optionalFitCenter().centerInside().centerCrop()
        )
        .transition(DrawableTransitionOptions.withCrossFade(300))
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

