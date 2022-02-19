package com.kkunan.randomdogandroid.features.randomimage.domain.repositories

import com.kkunan.randomdogandroid.common.domain.responses.LocalResponse
import com.kkunan.randomdogandroid.features.randomimage.domain.entities.DogImage

interface RandomDogRepository {
    suspend fun randomImage(number: Int): LocalResponse<List<DogImage>>
}