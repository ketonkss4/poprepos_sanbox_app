package com.pmd.droidexihibition.popularreposapp.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pmd.droidexihibition.popularreposapp.persistence.model.GitOrganization

@Dao interface OrganizationDao {
    @Query("SELECT * FROM gitorganization WHERE name = :orgName")
    fun findOrganization(orgName : String) : LiveData<GitOrganization>

    @Query("SELECT * FROM gitorganization")
    fun getAllOrganizations() : LiveData<List<GitOrganization>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(organization: GitOrganization)
}