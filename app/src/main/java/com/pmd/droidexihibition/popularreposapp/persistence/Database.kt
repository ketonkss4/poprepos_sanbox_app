package com.pmd.droidexihibition.popularreposapp.persistence

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.pmd.droidexihibition.popularreposapp.persistence.model.GitOrganization
import com.pmd.droidexihibition.popularreposapp.persistence.model.PopRepo
import javax.inject.Inject

class Database @Inject constructor(private val organizationDao: OrganizationDao) : RepoDataSource {

    override fun getAllOrganizations(): LiveData<List<GitOrganization>> {
        return organizationDao.getAllOrganizations()
    }

    override fun findPopularReposForOrganization(searchText: String): LiveData<List<PopRepo>> {
        return Transformations.switchMap(organizationDao.findOrganization(searchText)) { organization ->
            val mutableLiveData = MutableLiveData<List<PopRepo>>()
            mutableLiveData.value = organization.popularRepos
            mutableLiveData
        }
    }

    override fun saveOrganization(organization: GitOrganization) {
        organizationDao.insert(organization)
    }
}