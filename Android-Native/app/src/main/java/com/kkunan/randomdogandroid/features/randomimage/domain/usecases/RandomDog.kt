package com.kkunan.randomdogandroid.features.randomimage.domain.usecases

import com.kkunan.randomdogandroid.common.domain.responses.LocalResponse
import com.kkunan.randomdogandroid.features.randomimage.domain.entities.DogImage
import com.kkunan.randomdogandroid.features.randomimage.domain.repositories.RandomDogRepository
import javax.inject.Inject

class RandomDog @Inject constructor (private val repository: RandomDogRepository) {
    suspend fun randomImage(number: Int = 1): LocalResponse<List<DogImage>>{
        return repository.randomImage(number)
    }
}