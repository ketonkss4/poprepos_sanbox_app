package com.pmd.droidexihibition.popularreposapp.persistence

import androidx.lifecycle.LiveData
import com.pmd.droidexihibition.popularreposapp.persistence.model.GitOrganization
import com.pmd.droidexihibition.popularreposapp.persistence.model.OrganizationWithRepos
import com.pmd.droidexihibition.popularreposapp.persistence.model.PopRepo
import javax.inject.Inject

class Database @Inject constructor(
    private val organizationDao: OrganizationDao,
    private val popRepoDao: PopRepoDao
) : RepoDataSource {

    override fun getAllOrganizationsWithRepos(): LiveData<List<OrganizationWithRepos>> {
        return organizationDao.getAllOrganizationsWithRepos()
    }

    override fun saveOrganization(organization: GitOrganization) {
        organizationDao.insertOrganizations(organization)
    }

    override fun saveRepositories(popRepos: List<PopRepo>) {
        popRepos.forEach {
            popRepoDao.insertRepo(it)
        }
    }
}