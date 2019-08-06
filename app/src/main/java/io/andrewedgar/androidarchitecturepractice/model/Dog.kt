package io.andrewedgar.androidarchitecturepractice.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity // Marks the class for formatting into a Room database item later/ We can rename table on this line if we want
data class Dog(


    // @SerializedName denotes which value in the Json response will be used to fill the local variable
    // @ColumnInfo renames the Room database column that each variable will be stored in, otherwise the variable name
    // will become the column name

    @ColumnInfo(name = "breed_id")
    @SerializedName("id")
    val breedID: String?,

    @ColumnInfo(name = "dog_breed")
    @SerializedName("name")
    val dogBreed: String?,

    @ColumnInfo(name = "life_span")
    @SerializedName("life_span")
    val lifespan: String?,

    @ColumnInfo(name = "breed_group")
    @SerializedName("breed_group")
    val breedGroup: String?,

    @ColumnInfo(name = "bred_for")
    @SerializedName("bred_for")
    val bredFor: String?,

    @SerializedName("temperament")
    val temperament: String?,

    @ColumnInfo(name = "dog_url")
    @SerializedName("url")
    val imageUrl: String?
) {

    //Generates a unique ID for each item placed into the database
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0

}