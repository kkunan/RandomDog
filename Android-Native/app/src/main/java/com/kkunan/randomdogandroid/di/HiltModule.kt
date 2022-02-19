package com.kkunan.randomdogandroid.di

import com.kkunan.randomdogandroid.features.randomimage.data.apis.DogCeoApiInterface
import com.kkunan.randomdogandroid.features.randomimage.data.datasources.RandomDogNetworkDatasource
import com.kkunan.randomdogandroid.features.randomimage.data.datasources.RandomDogNetworkDatasourceImpl
import com.kkunan.randomdogandroid.features.randomimage.data.repositories.RandomDogRepositoryImpl
import com.kkunan.randomdogandroid.features.randomimage.domain.repositories.RandomDogRepository
import com.kkunan.randomdogandroid.features.randomimage.domain.usecases.RandomDog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class HiltModule {

    @Provides
    fun provideDogCeoApi(): DogCeoApiInterface = DogCeoApiInterface.create()

    @Provides
    fun provideRandomDogNetworkDatasource(ceoApiInterface: DogCeoApiInterface): RandomDogNetworkDatasource =
        RandomDogNetworkDatasourceImpl(ceoApiInterface)

    @Provides
    fun provideRandomDogRepository(networkDatasource: RandomDogNetworkDatasource): RandomDogRepository =
        RandomDogRepositoryImpl(networkDatasource)

    @Provides
    fun provideRandomDog(repository: RandomDogRepository) = RandomDog(repository)
}