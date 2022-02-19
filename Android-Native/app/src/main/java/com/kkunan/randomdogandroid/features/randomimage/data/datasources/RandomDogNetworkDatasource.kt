package com.kkunan.randomdogandroid.features.randomimage.data.datasources

import com.kkunan.randomdogandroid.features.randomimage.data.models.RandomDogImageResponseModel

interface RandomDogNetworkDatasource {
    suspend fun getRandomImage(number: Int): RandomDogImageResponseModel
}