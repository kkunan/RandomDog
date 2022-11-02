package com.kkunan.kmm.randomdog.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RandomImagesResponse(
    @SerialName("message") val imageUrls: List<String>? = null,
    @SerialName("status") val status: String? = null
)
