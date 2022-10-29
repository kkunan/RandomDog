package com.kkunan.kmm.randomdog.domain.repositories

import com.kkunan.kmm.randomdog.domain.entities.DogImage

interface DogImageRepository {
    suspend fun randomImage(number: Int): List<DogImage>?
}