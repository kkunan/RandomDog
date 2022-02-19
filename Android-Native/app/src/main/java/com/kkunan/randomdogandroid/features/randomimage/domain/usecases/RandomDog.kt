package com.kkunan.randomdogandroid.features.randomimage.domain.usecases

import com.kkunan.randomdogandroid.common.domain.responses.LocalResponse
import com.kkunan.randomdogandroid.features.randomimage.domain.entities.DogImage

class RandomDog {
    suspend fun randomImage(number: Int = 1): LocalResponse<List<DogImage>>{
        throw NotImplementedError("")
    }
}