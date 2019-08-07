package io.andrewedgar.androidarchitecturepractice.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import io.andrewedgar.androidarchitecturepractice.model.Dog
import io.andrewedgar.androidarchitecturepractice.model.DogDatabase
import io.andrewedgar.androidarchitecturepractice.utils.DogsApiService
import io.andrewedgar.androidarchitecturepractice.utils.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : BaseViewModel(application) {


    private var prefHelper = SharedPreferencesHelper(getApplication())
    private var refreshTime = 5 * 60 * 1000 * 1000 * 1000L


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

        val updateTime = prefHelper.getUpdateTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            fetchFromDatabase()
        } else {

            fetchFromRemote()
        }
    }

    fun refreshBypassCache(){
        fetchFromRemote()
    }

    private fun fetchFromDatabase() {
        loading.value = true
        launch {
            val dogs = DogDatabase(getApplication()).dogDao().getAllDogs()
            makeListOfRetrievedDogs(dogs)
            Toast.makeText(getApplication(),"Dogs retrieved from Database", Toast.LENGTH_SHORT).show()
        }
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
                    override fun onSuccess(dogList: List<Dog>) {

                        storeDogsLocally(dogList)
                        Toast.makeText(getApplication(),"Dogs retrieved from Database", Toast.LENGTH_SHORT).show()


                    }

                    override fun onError(e: Throwable) {
                        dogsLoadError.value = true
                        loading.value = false
                        e.printStackTrace()

                    }

                })
        )
    }

    private fun makeListOfRetrievedDogs(dogList: List<Dog>) {
        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false
    }

    private fun storeDogsLocally(list: List<Dog>) {


        //creates coroutine to run database operations in the background
        launch {

            //gets instance of dao to handle database operations in this method
            val dao = DogDatabase(getApplication()).dogDao()



            dao.deleteAllDogs()

            //Puts list in array and prepares it for insertion
            val result = dao.insertAll(*list.toTypedArray())

            var i = 0

            while (i < list.size) {
                list[i].uuid = result[i].toInt()
                i++
            }

            makeListOfRetrievedDogs(list)
        }

        prefHelper.saveUpdateTime(System.nanoTime())

    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}