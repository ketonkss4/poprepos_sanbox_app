package com.pmd.droidexihibition.popularreposapp.persistence

import android.util.Log
import androidx.lifecycle.LiveData
import com.pmd.droidexihibition.popularreposapp.persistence.model.GitOrganization
import com.pmd.droidexihibition.popularreposapp.persistence.model.OrganizationWithRepos
import com.pmd.droidexihibition.popularreposapp.persistence.model.PopRepo
import java.lang.Exception
import javax.inject.Inject

class Database @Inject constructor(
    private val organizationDao: OrganizationDao,
    private val popRepoDao: PopRepoDao
) : RepoDataSource {

    override fun getAllOrganizationsWithRepos(): LiveData<List<OrganizationWithRepos>> {
        return organizationDao.getAllOrganizationsWithRepos()
    }

    override fun saveOrganization(organization: GitOrganization) {
        try {
            organizationDao.insertOrganizations(organization)
        } catch (e: Exception) {
            Log.e(Database::class.java.simpleName, e.toString())
        }
    }

    override fun saveRepositories(popRepos: List<PopRepo>) {
        try {
            popRepos.forEach {
                popRepoDao.insertRepo(it)
            }
        } catch (e: Exception) {
            Log.v(Database::class.java.simpleName, e.toString())
        }
    }
}