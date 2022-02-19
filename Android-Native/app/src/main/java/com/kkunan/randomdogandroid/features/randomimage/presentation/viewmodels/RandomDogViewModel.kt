package com.kkunan.randomdogandroid.features.randomimage.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kkunan.randomdogandroid.common.domain.responses.LocalResponse
import com.kkunan.randomdogandroid.features.randomimage.domain.entities.DogImage
import com.kkunan.randomdogandroid.features.randomimage.domain.usecases.RandomDog

class RandomDogViewModel(private val randomDog: RandomDog): ViewModel() {
    val isLoading = MutableLiveData(false)
    val dogImageUrl = MutableLiveData<List<DogImage>>()

    suspend fun randomNewImage(){
        isLoading.postValue(true)

        val response = randomDog.randomImage(1)
        if (response is LocalResponse.SUCCESS){
            dogImageUrl.postValue(response.value)
        }

        isLoading.postValue(false)
    }
}