package com.pmd.droidexihibition.popularreposapp.concurrency

import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatchers {
    fun ioDispatcher(): CoroutineDispatcher
    fun uiDispatcher() : CoroutineDispatcher
    fun testDispatcher() : CoroutineDispatcher
}