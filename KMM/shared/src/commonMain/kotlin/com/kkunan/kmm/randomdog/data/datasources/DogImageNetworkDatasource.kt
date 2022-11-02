package com.kkunan.kmm.randomdog.data.datasources

import com.kkunan.kmm.randomdog.data.api.HttpClientWrapper
import com.kkunan.kmm.randomdog.data.models.RandomImagesRequest
import com.kkunan.kmm.randomdog.data.models.RandomImagesResponse
import io.ktor.client.call.*

interface DogImageNetworkDatasource {
    suspend fun getRandomImages(request: RandomImagesRequest): RandomImagesResponse
}

class DogImageNetworkDatasourceImpl(private val httpClient: HttpClientWrapper) :
    DogImageNetworkDatasource {
    companion object {
        const val endpoint = "https://dog.ceo/api/breeds/image/random"
    }

    override suspend fun getRandomImages(request: RandomImagesRequest): RandomImagesResponse {
        try {
            val httpResponse =
                httpClient.get("https://dog.ceo/api/breeds/image/random/${request.number}")
            return httpResponse.body()
        } catch (_: Exception) {
            return RandomImagesResponse(null, null)
        }
    }
}