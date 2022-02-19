package com.kkunan.randomdogandroid.features.randomimage.data.datasources

import com.kkunan.randomdogandroid.features.randomimage.data.apis.DogCeoApiInterface
import com.kkunan.randomdogandroid.features.randomimage.data.models.RandomDogImageResponseModel

interface RandomDogNetworkDatasource {
    suspend fun getRandomImage(number: Int): RandomDogImageResponseModel
}

class RandomDogNetworkDatasourceImpl(private val dogCeoApiInterface: DogCeoApiInterface): RandomDogNetworkDatasource {
    override suspend fun getRandomImage(number: Int): RandomDogImageResponseModel {
        return dogCeoApiInterface.getRandomDogImage(number)
    }
}