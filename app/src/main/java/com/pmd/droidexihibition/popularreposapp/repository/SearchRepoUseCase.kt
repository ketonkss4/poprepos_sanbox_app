package com.pmd.droidexihibition.popularreposapp.repository

import android.util.Log
import com.pmd.droidexihibition.popularreposapp.BuildConfig
import com.pmd.droidexihibition.popularreposapp.api.GithubApiService
import com.pmd.droidexihibition.popularreposapp.persistence.RepoDataSource
import com.pmd.droidexihibition.popularreposapp.persistence.model.GitOrganization
import com.pmd.droidexihibition.popularreposapp.persistence.model.PopRepo
import javax.inject.Inject

class SearchRepoUseCase @Inject constructor(
    private val repoDataSource: RepoDataSource,
    private val apiService: GithubApiService
) :
    RepoUseCase {

    override suspend fun performAsync(searchText: String) {
        searchPopularRepos(searchText)
    }

    private suspend fun searchPopularRepos(searchText: String) {
        try {
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

        } catch (e: Exception) {
            Log.e(Repository::class.java.simpleName, e.cause.toString())
        }
    }

}