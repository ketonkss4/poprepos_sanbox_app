package com.pmd.droidexihibition.popularreposapp.persistence

import androidx.lifecycle.LiveData
import com.pmd.droidexihibition.popularreposapp.persistence.model.GitOrganization
import com.pmd.droidexihibition.popularreposapp.persistence.model.OrganizationWithRepos
import com.pmd.droidexihibition.popularreposapp.persistence.model.PopRepo

interface RepoDataSource {
    fun getAllOrganizationsWithRepos(): LiveData<List<OrganizationWithRepos>>
    fun saveOrganization(organization: GitOrganization)
    fun saveRepositories(popRepos: List<PopRepo>)
}