package io.andrewedgar.androidarchitecturepractice.utils

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.andrewedgar.androidarchitecturepractice.R



//Extends the ImageView class with a new method to load and cache images via glide, as well as set progress
// drawables for each view

fun ImageView.loadImage(uri: String, progressDrawable: CircularProgressDrawable) {


    val options = RequestOptions().placeholder(progressDrawable).error(R.drawable.ic_launcher_background)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri).into(this)

}



//Method that will be attached to ImageView in xml @BindingAdapter annotation makes a method available to a layout and
// binding it to the specified attribute

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, url: String) {
    view.loadImage(url, getProgressDrawable(view.context))
}

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }


}