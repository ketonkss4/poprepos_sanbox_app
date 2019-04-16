package com.pmd.droidexihibition.popularreposapp.di.component

import android.content.Context
import com.pmd.droidexihibition.popularreposapp.RepoSearchApp
import com.pmd.droidexihibition.popularreposapp.concurrency.AppDispatchers
import com.pmd.droidexihibition.popularreposapp.di.module.*
import com.pmd.droidexihibition.popularreposapp.persistence.RepoDataSource
import com.pmd.droidexihibition.popularreposapp.repository.RepoUseCase
import com.pmd.droidexihibition.popularreposapp.ui.RepoSearchFragment
import com.pmd.droidexihibition.popularreposapp.ui.ViewModelFactory
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ConcurrencyModule::class,
        DatabaseModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        FragmentModule::class
    ]
)
interface AppComponent {

    fun inject(into: RepoSearchFragment)

    fun provideAppContext(): Context

    fun provideAppDispatchers(): AppDispatchers

    fun provideDatabase(): RepoDataSource

    fun provideRepoUseCase(): RepoUseCase

    fun provideViewModelFactory(): ViewModelFactory

    fun inject(into: RepoSearchApp)
}