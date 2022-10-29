package com.kkunan.kmm.randomdog

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform