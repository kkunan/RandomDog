package com.kkunan.randomdogandroid.features.randomimage.data.repositories

import com.kkunan.randomdogandroid.common.domain.responses.LocalResponse
import com.kkunan.randomdogandroid.features.randomimage.data.datasources.RandomDogNetworkDatasource
import com.kkunan.randomdogandroid.features.randomimage.domain.entities.DogImage
import com.kkunan.randomdogandroid.features.randomimage.domain.repositories.RandomDogRepository

class RandomDogRepositoryImpl(private val networkDatasource: RandomDogNetworkDatasource) : RandomDogRepository {
    override suspend fun randomImage(number: Int): LocalResponse<List<DogImage>> {
        val response = networkDatasource.getRandomImage(number)
        if (response.status == "success"){
            return LocalResponse.SUCCESS(response.imageUrl?.map {
                DogImage(it)
            }?.toList() ?: emptyList())
        }
        else return LocalResponse.FAILURE("Cannot get dog images :'(")

    }
}