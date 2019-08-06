package io.andrewedgar.androidarchitecturepractice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.andrewedgar.androidarchitecturepractice.model.Dog
import io.andrewedgar.androidarchitecturepractice.utils.DogsApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {


    private val dogsService = DogsApiService()

    //Handles disposal of observable to prevent data leaks API call is added to this when it is made and disposed on
    //in the ViewModel's onClear method


    private val disposable = CompositeDisposable()

    val dogs = MutableLiveData<List<Dog>>()

    //To tell when there is an error
    val dogsLoadError = MutableLiveData<Boolean>()

    //To tell when data is being loaded from API
    val loading = MutableLiveData<Boolean>()

    fun refresh() {

        fetchFromRemote()

    }


    private fun fetchFromRemote() {

        loading.value = true
        disposable.add(
            dogsService.getDogs()
                //Makes API call on background thread
                .subscribeOn(Schedulers.newThread())
                //Passes results to Main Thread
                .observeOn(AndroidSchedulers.mainThread())
                //Subsribes to data changes
                .subscribeWith(object : DisposableSingleObserver<List<Dog>>() {
                    override fun onSuccess(t: List<Dog>) {
                        dogs.value = t
                        dogsLoadError.value=false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        dogsLoadError.value=true
                        loading.value=false
                        e.printStackTrace()

                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}