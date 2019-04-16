package com.pmd.droidexihibition.popularreposapp.di.module

import com.pmd.droidexihibition.popularreposapp.concurrency.AppDispatchers
import com.pmd.droidexihibition.popularreposapp.concurrency.KtDispatchers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ConcurrencyModule {
    @Provides
    @Singleton
    fun appDispatchers(): AppDispatchers = KtDispatchers()
}