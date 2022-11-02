package com.kkunan.kmm.randomdog.data.api

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient

interface HttpClientWrapper {
    suspend fun get(urlString: String): HttpResponse
}

class HttpClientWrapperImpl(private val httpClient: HttpClient) : HttpClientWrapper {
    override suspend fun get(urlString: String) = httpClient.get(urlString)
}