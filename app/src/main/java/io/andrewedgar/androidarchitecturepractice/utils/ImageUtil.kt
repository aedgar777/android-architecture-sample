package io.andrewedgar.androidarchitecturepractice.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.andrewedgar.androidarchitecturepractice.R

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }


}

//Extends the ImageView class with a new method to load and cache images via glide, as well as set progress
// drawables for each view

fun ImageView.loadImage(uri:String, progressDrawable: CircularProgressDrawable){


    val options = RequestOptions().placeholder(progressDrawable).error(R.drawable.ic_launcher_background)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri).into(this)

}