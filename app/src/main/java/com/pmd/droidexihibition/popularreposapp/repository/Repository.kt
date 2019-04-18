package com.pmd.droidexihibition.popularreposapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.pmd.droidexihibition.popularreposapp.persistence.RepoDataSource
import com.pmd.droidexihibition.popularreposapp.persistence.model.PopRepo
import com.pmd.droidexihibition.popularreposapp.ui.model.Result
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: RepoDataSource,
    private val useCase: RepoUseCase
) {

    fun getRepoLiveData(searchText: String): LiveData<List<PopRepo>> {
        return Transformations.switchMap(localDataSource.getAllOrganizationsWithRepos()) { organizations ->
            val repoLiveData = MutableLiveData<List<PopRepo>>()
            val targetOrganization = organizations.firstOrNull { gitOrganization -> gitOrganization.organization.name == searchText }
            if (targetOrganization != null) {
                repoLiveData.value = targetOrganization.popRepos
            } else {
                repoLiveData.value = emptyList()
            }
            repoLiveData
        }
    }

    suspend fun searchReposAsync(searchText: String) : Result<Boolean> {
        return useCase.performAsync(searchText)
    }
}