package com.kkunan.kmm.randomdog.presentation.viewstatehandler

import com.kkunan.kmm.randomdog.domain.usecases.RandomDogImage
import com.kkunan.kmm.randomdog.presentation.uistates.RandomDogUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RandomDogViewStateHandler(
    initialState: RandomDogUiState = RandomDogUiState(),
    private val randomDogImage: RandomDogImage
) {
    private var _uiState = initialState
    val uiState: RandomDogUiState = _uiState

    suspend fun randomNextImage(stateListener: (RandomDogUiState) -> Unit) {
        if (_uiState.isLoading) {
            return
        }
        _uiState = _uiState.copy(isLoading = true)
        stateListener(_uiState)

        val response = randomDogImage()
        _uiState = _uiState.copy(isLoading = false, dogImageUrl = response?.imageUrl)
        stateListener(_uiState)
    }
}