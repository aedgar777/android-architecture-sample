package io.andrewedgar.androidarchitecturepractice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.andrewedgar.androidarchitecturepractice.model.Dog

class DetailViewModel : ViewModel() {

    val dogLiveData=  MutableLiveData<Dog>()


    fun fetch() {

        val dog = Dog("1","Corgie","15", "breedGroup","bredfor", "temperament","")

        dogLiveData.value = dog


    }
}