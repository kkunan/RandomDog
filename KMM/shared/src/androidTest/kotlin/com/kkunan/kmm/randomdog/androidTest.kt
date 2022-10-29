package com.kkunan.kmm.randomdog

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", Greeting().greeting().contains("Android"))
    }
}