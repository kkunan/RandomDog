package com.kkunan.kmm.randomdog.domain.usecases

import com.kkunan.kmm.randomdog.domain.entities.DogImage
import com.kkunan.kmm.randomdog.domain.repositories.DogImageRepository

class RandomDogImage(private val repository: DogImageRepository) {
    suspend operator fun invoke(): DogImage?{
        val response = repository.randomImage(1)
        return response?.let { images ->
            if (images.isEmpty()){
                null
            } else {
                images[0]
            }
        }
    }
}