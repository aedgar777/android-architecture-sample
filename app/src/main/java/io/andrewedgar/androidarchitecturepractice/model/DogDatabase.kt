package io.andrewedgar.androidarchitecturepractice.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [Dog::class],
    version = 1
)// Marks this class as a database and lists the data types that can be put in it
abstract class DogDatabase : RoomDatabase() {

    abstract fun dogDao(): DogDao

    companion object {
        @Volatile
        private var instance: DogDatabase? = null
        private val LOCK = Any()




        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) { //synchronized makes sure that only one thread can access the database at a time

            //elvis operator (?:) provides extra logic in case variable it is called on is null
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DogDatabase::class.java,
            "dogDatabase"
        ).build()
    }
}