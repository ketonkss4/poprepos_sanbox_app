package com.pmd.droidexihibition.popularreposapp.di.module

import com.pmd.droidexihibition.popularreposapp.api.GithubApiService
import com.pmd.droidexihibition.popularreposapp.persistence.RepoDataSource
import com.pmd.droidexihibition.popularreposapp.repository.RepoUseCase
import com.pmd.droidexihibition.popularreposapp.repository.SearchRepoUseCase
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun providesRepoUseCase(
        repoDataSource: RepoDataSource,
        apiService: GithubApiService
    ): RepoUseCase {
        return SearchRepoUseCase(repoDataSource, apiService)
    }
}