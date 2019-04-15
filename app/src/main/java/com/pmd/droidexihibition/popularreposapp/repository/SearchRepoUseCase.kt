package com.pmd.droidexihibition.popularreposapp.repository

import com.pmd.droidexihibition.popularreposapp.BuildConfig
import com.pmd.droidexihibition.popularreposapp.api.GithubApiService
import com.pmd.droidexihibition.popularreposapp.persistence.RepoDataSource
import com.pmd.droidexihibition.popularreposapp.persistence.model.GitOrganization
import javax.inject.Inject

class SearchRepoUseCase @Inject constructor(private val repoDataSource: RepoDataSource, private val apiService: GithubApiService) :
    RepoUseCase {

    override suspend fun performAsync(searchText: String) {
        searchPopularRepos(searchText)
    }

    private suspend fun searchPopularRepos(searchText: String) {
        try {
            val response = apiService.searchPublicReposByOrg(searchText).await()
            val popularRepos =
                response.sortedByDescending { repo -> repo.stargazers_count }.take(BuildConfig.REPO_SEARCH_LIMIT)
            if (popularRepos.isNotEmpty()) {
                repoDataSource.saveOrganization(GitOrganization(searchText, popularRepos))
            }

        } catch (e: Exception) {

        }
    }

}