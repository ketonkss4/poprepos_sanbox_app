package com.pmd.droidexihibition.popularreposapp.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pmd.droidexihibition.popularreposapp.persistence.model.GitOrganization
import com.pmd.droidexihibition.popularreposapp.persistence.model.OrganizationWithRepos
import com.pmd.droidexihibition.popularreposapp.persistence.model.PopRepo

@Dao
interface OrganizationDao {
    @Query("SELECT * FROM Organizations WHERE name = :orgName")
    fun findOrganization(orgName: String): LiveData<GitOrganization>

    @Query("SELECT * FROM Organizations")
    fun getAllOrganizations(): LiveData<List<GitOrganization>>

    @Query("SELECT * FROM Organizations")
    fun getAllOrganizationsWithRepos(): LiveData<List<OrganizationWithRepos>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrganizations(organization: GitOrganization)
}