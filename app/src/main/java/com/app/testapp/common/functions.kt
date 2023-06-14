package com.app.testapp.common

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.app.testapp.model.IntentData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


@BindingAdapter("load_image")
fun setImageViewResource(imageView: ImageView, resource: String?) {

    resource?.let { imageView.loadImageFromUrl(it) }
}

fun ImageView.loadImageFromUrl(
    aImageUrl: String
) {
    if (aImageUrl.isNotEmpty()) {
        Glide.with(context)
            .load(Uri.parse(aImageUrl))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
    }
}


inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}