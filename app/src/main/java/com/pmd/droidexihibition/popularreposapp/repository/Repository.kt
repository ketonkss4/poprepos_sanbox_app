package com.pmd.droidexihibition.popularreposapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.pmd.droidexihibition.popularreposapp.persistence.RepoDataSource
import com.pmd.droidexihibition.popularreposapp.persistence.model.PopRepo
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: RepoDataSource,
    private val useCase: RepoUseCase
) {

    suspend fun getRepositories(searchText: String): LiveData<List<PopRepo>> {
       val repoLiveData = Transformations.switchMap(localDataSource.getAllOrganizations()) { organizations ->
            val repoLiveData = MutableLiveData<List<PopRepo>>()
            val targetOrganization = organizations
                .filter { gitOrganization -> gitOrganization.name == searchText }
                .firstOrNull()
            if (targetOrganization != null) {
                repoLiveData.value = targetOrganization.popularRepos
            } else {
                repoLiveData.value = emptyList()
            }
            repoLiveData
        }

        if(repoLiveData.value.isNullOrEmpty()) useCase.performAsync(searchText)
        return repoLiveData
    }

}