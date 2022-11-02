@file:OptIn(ExperimentalCoroutinesApi::class)

package com.kkunan.kmm.randomdog.data.datasources

import com.kkunan.kmm.randomdog.data.api.HttpClientWrapper
import com.kkunan.kmm.randomdog.data.models.RandomImagesRequest
import com.kkunan.kmm.randomdog.data.models.RandomImagesResponse
import com.kkunan.kmm.randomdog.getRandomString
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.random.Random
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals


internal class DogImageNetworkDatasourceImplTest {

    lateinit var httpClient: HttpClientWrapper
    lateinit var datasource: DogImageNetworkDatasourceImpl

    @BeforeTest
    fun setUp() {
        httpClient = mockk(relaxed = true)
        datasource = DogImageNetworkDatasourceImpl(httpClient)
    }

    @Test
    fun `getRandomImages should call httpClient get with correct endpoint`() {
        runTest {
            // arrange
            coEvery { httpClient.get(urlString = any()) } returns mockk<HttpResponse>()
            val inputNumber = Random.nextInt()

            // act
            datasource.getRandomImages(RandomImagesRequest(inputNumber))

            // assert
            coVerify {
                val expected = "${DogImageNetworkDatasourceImpl.endpoint}/${inputNumber}"
                httpClient.get(urlString = expected)
            }
        }
    }

    @Test
    fun `getRandomImages should return empty response if httpClient throws exception`(){
       runTest {
           // arrange
           coEvery {
               httpClient.get(any())
           } throws Exception()

           // act
           val response = datasource.getRandomImages(RandomImagesRequest(Random.nextInt()))

           // assert
           assertEquals(response, RandomImagesResponse())
       }
    }

    @Test
    fun `getRandomImages should return response body from datasource response`(){
        runTest {
            // arrange
            val expected: RandomImagesResponse = mockk {
                every { status } returns getRandomString(4)
            }
            val httpResponse: HttpResponse = mockk {
                coEvery { body<RandomImagesResponse>() } returns expected
            }
            coEvery {  httpClient.get(any())} returns httpResponse

            // act
            val response = datasource.getRandomImages(RandomImagesRequest(Random.nextInt()))

            // assert
            assertEquals(response, expected)
        }
    }

    @Test
    fun `getRandomImages should return empty response if response body throws exception`(){
        runTest {
            // arrange
            val httpResponse: HttpResponse = mockk {
                coEvery { body<RandomImagesResponse>() } throws Exception()
            }
            coEvery {  httpClient.get(any())} returns httpResponse

            // act
            val response = datasource.getRandomImages(RandomImagesRequest(Random.nextInt()))

            // assert
            assertEquals(response, RandomImagesResponse())
        }
    }
}