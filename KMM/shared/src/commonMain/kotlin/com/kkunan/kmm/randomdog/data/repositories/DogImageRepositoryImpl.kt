package com.kkunan.kmm.randomdog.data.repositories

import com.kkunan.kmm.randomdog.data.datasources.DogImageNetworkDatasource
import com.kkunan.kmm.randomdog.data.datasources.NetworkStatusDatasource
import com.kkunan.kmm.randomdog.data.models.RandomImagesRequest
import com.kkunan.kmm.randomdog.domain.entities.DogImage
import com.kkunan.kmm.randomdog.domain.repositories.DogImageRepository

class DogImageRepositoryImpl(private val networkDatasource: NetworkStatusDatasource,
                             private val dogImageNetworkDatasource: DogImageNetworkDatasource) : DogImageRepository {
    override suspend fun randomImage(number: Int): List<DogImage>? {
        return if(networkDatasource.isConnected){
            try{
                val response = dogImageNetworkDatasource.getRandomImages(RandomImagesRequest(number))
                response.imageUrls?.map {
                    DogImage(it)
                }
            } catch (_: Exception){
                null
            }
        } else {
            null
        }
    }
}