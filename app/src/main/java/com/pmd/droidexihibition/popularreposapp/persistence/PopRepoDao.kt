package com.pmd.droidexihibition.popularreposapp.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pmd.droidexihibition.popularreposapp.persistence.model.PopRepo

@Dao
interface PopRepoDao {

    @Query("SELECT * FROM PopularRepositories WHERE orgId = :orgId")
    fun getPopularReposForOrg(orgId: Int): LiveData<List<PopRepo>>

    @Query("SELECT * FROM PopularRepositories")
    fun getAllRepos(): LiveData<List<PopRepo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepo(popRepo: PopRepo)
}