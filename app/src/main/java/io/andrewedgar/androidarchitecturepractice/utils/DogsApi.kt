package io.andrewedgar.androidarchitecturepractice.utils

import io.andrewedgar.androidarchitecturepractice.model.Dog
import io.reactivex.Single
import retrofit2.http.GET




interface DogsApi {


    @GET ("DevTides/DogsApi/master/dogs.json")
    fun getDogs(): Single<List<Dog>>
}