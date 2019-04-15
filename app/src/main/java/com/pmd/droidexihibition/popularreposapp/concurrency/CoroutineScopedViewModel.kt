package com.pmd.droidexihibition.popularreposapp.concurrency

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class CoroutineScopedViewModel : ViewModel() , CoroutineScope {
    abstract val dispatchers: AppDispatchers
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + dispatchers.uiDispatcher()

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}