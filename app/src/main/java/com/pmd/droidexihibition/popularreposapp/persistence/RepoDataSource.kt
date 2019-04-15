package com.pmd.droidexihibition.popularreposapp.persistence

import androidx.lifecycle.LiveData
import com.pmd.droidexihibition.popularreposapp.persistence.model.GitOrganization
import com.pmd.droidexihibition.popularreposapp.persistence.model.PopRepo

interface RepoDataSource {

    fun findPopularReposForOrganization(searchText: String): LiveData<List<PopRepo>>

    fun getAllOrganizations() : LiveData<List<GitOrganization>>

    fun saveOrganization(organization: GitOrganization)
}