package io.andrewedgar.androidarchitecturepractice.view

import android.view.View

//Interface providing onClick functionality to list items

interface DogClickListener {

    fun onDogClicked(v:View)
}