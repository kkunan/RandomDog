package com.kkunan.kmm.randomdog.di

import com.kkunan.kmm.randomdog.data.api.HttpClientWrapper
import com.kkunan.kmm.randomdog.data.api.HttpClientWrapperImpl
import com.kkunan.kmm.randomdog.data.api.httpClient
import com.kkunan.kmm.randomdog.data.datasources.*
import com.kkunan.kmm.randomdog.data.repositories.DogImageRepositoryImpl
import com.kkunan.kmm.randomdog.domain.repositories.DogImageRepository
import com.kkunan.kmm.randomdog.domain.usecases.RandomDogImage
import com.kkunan.kmm.randomdog.presentation.viewstatehandler.RandomDogViewStateHandler
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        modules(appModule())
    }
}

fun appModule() = listOf(module {
    single<NetworkStatusDatasource> {
        NetworkStatusDatasourceImpl { getConnectionStatus() }
    }
    single<HttpClientWrapper> { HttpClientWrapperImpl(httpClient {}) }
    single<DogImageNetworkDatasource> {
        DogImageNetworkDatasourceImpl(
            get()
        )
    }
    single<DogImageRepository> { DogImageRepositoryImpl(get(), get()) }
    single { RandomDogImage(get()) }
    factory { RandomDogViewStateHandler(randomDogImage = get()) }
})