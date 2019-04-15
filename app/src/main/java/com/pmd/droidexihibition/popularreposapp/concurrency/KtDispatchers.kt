package com.pmd.droidexihibition.popularreposapp.concurrency

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi


class KtDispatchers : AppDispatchers {

    override fun ioDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    override fun uiDispatcher() : CoroutineDispatcher {
        return Dispatchers.Main
    }

    @ExperimentalCoroutinesApi
    override fun testDispatcher() : CoroutineDispatcher {
        return Dispatchers.Unconfined
    }
}