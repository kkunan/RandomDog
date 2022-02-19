package com.kkunan.randomdogandroid.features.randomimage.data.apis

import com.kkunan.randomdogandroid.features.randomimage.data.models.RandomDogImageResponseModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface DogCeoApiInterface {
    @GET("breeds/image/random/{number}")
    suspend fun getRandomDogImage(@Path("number") number: Int) : RandomDogImageResponseModel

    companion object {

        var BASE_URL = "https://dog.ceo/api/"
        fun create() : DogCeoApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(DogCeoApiInterface::class.java)

        }
    }
}