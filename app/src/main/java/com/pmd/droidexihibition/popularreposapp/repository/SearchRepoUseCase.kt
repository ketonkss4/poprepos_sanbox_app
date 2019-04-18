package com.pmd.droidexihibition.popularreposapp.repository

import com.pmd.droidexihibition.popularreposapp.BuildConfig
import com.pmd.droidexihibition.popularreposapp.api.GithubApiService
import com.pmd.droidexihibition.popularreposapp.persistence.RepoDataSource
import com.pmd.droidexihibition.popularreposapp.persistence.model.GitOrganization
import com.pmd.droidexihibition.popularreposapp.persistence.model.PopRepo
import com.pmd.droidexihibition.popularreposapp.ui.model.Result
import com.pmd.droidexihibition.popularreposapp.ui.model.SearchError
import com.pmd.droidexihibition.popularreposapp.ui.model.Success
import javax.inject.Inject

class SearchRepoUseCase @Inject constructor(
    private val repoDataSource: RepoDataSource,
    private val apiService: GithubApiService
) : RepoUseCase {

    override suspend fun performAsync(searchText: String): Result<Boolean> {
        return try {
            searchPopularRepos(searchText)
            Success(true)
        } catch (e: Exception) {
            //TODO create meaningful error states
            SearchError(e)
        }
    }

    private suspend fun searchPopularRepos(searchText: String) {
        val response = apiService.searchPublicReposByOrgAsync(searchText).await()
        val popularRepos =
            response.sortedByDescending { repo -> repo.stargazers_count }.take(BuildConfig.REPO_SEARCH_LIMIT)
        if (popularRepos.isNotEmpty()) {
            repoDataSource.saveRepositories(popularRepos.map {
                PopRepo(
                    it.id,
                    it.owner.id,
                    it.name,
                    it.description,
                    it.html_url,
                    it.stargazers_count
                )
            })

            repoDataSource.saveOrganization(
                GitOrganization(
                    id = popularRepos.first().owner.id,
                    name = searchText
                )
            )
        }
    }

}