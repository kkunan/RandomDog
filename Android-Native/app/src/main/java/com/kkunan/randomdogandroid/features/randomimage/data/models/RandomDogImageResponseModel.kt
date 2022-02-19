package com.kkunan.randomdogandroid.features.randomimage.data.models

import com.google.gson.annotations.SerializedName

data class RandomDogImageResponseModel(
    @SerializedName("message") val imageUrl: List<String>?,
    @SerializedName("status") val status: String?
)

//{
//    "message": [
//    "https://images.dog.ceo/breeds/lhasa/n02098413_9259.jpg",
//    "https://images.dog.ceo/breeds/affenpinscher/n02110627_6310.jpg",
//    "https://images.dog.ceo/breeds/terrier-norfolk/20201118-norfolk.jpg"
//    ],
//    "status": "success"
//}