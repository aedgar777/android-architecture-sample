package io.andrewedgar.androidarchitecturepractice.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.andrewedgar.androidarchitecturepractice.model.Dog
import io.andrewedgar.androidarchitecturepractice.model.DogDatabase
import kotlinx.coroutines.launch

class DetailViewModel (application: Application) : BaseViewModel(application) {

    val dogLiveData = MutableLiveData<Dog>()


    fun fetch(dogUID: Int) {

        launch {
            val dao = DogDatabase(getApplication()).dogDao()

            val dog = dao.getDog(dogUID)


            dogLiveData.value = dog
        }


    }
}