package com.kkunan.kmm.randomdog.di

import com.kkunan.kmm.randomdog.presentation.viewstatehandler.RandomDogViewStateHandler
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RandomDogHelper : KoinComponent {
    private val stateHandler: RandomDogViewStateHandler by inject()
    fun getStateHandler() = stateHandler
}