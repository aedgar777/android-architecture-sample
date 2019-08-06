package io.andrewedgar.androidarchitecturepractice.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao // Interface through which we will access object in the Database
interface DogDao {

    @Insert // These annotations denote what kind of database operation will be performed by the following method

    // varargs denotes that an indefinite number of arguments may be passed to this method. suspend makes the function
    // wait until there is an available background thread to run the function

    suspend fun insertAll(vararg dogs: Dog): List<Long>


    @Query("SELECT * FROM dog")//Defines query to be made by method
    suspend fun getAllDogs(): List<Dog>

    @Query("SELECT* FROM dog WHERE uuid = :dogId")// You can pass arguments from the function into the query
    suspend fun getDog(dogId: Int): Dog

    @Query("DELETE FROM dog")
    suspend fun deleteAllDogs()
}