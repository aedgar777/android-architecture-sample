package io.andrewedgar.androidarchitecturepractice.model

import com.google.gson.annotations.SerializedName

data class Dog(


    //SerializedName denotes which value in the Json response will be used to fill the local variable

    @SerializedName("id")
    val breedID: String?,

    @SerializedName("name")
    val dogBreed: String?,

    @SerializedName("life_span")
    val lifespan: String?,

    @SerializedName("breed_group")
    val breedGroup: String?,

    @SerializedName("bred_for")
    val bredFor: String?,

    @SerializedName("temperament")
    val temperament: String?,

    @SerializedName("url")
    val imageUrl: String?
)