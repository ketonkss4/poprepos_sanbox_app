package com.pmd.droidexihibition.popularreposapp.di.module

import android.content.Context
import androidx.room.Room
import com.pmd.droidexihibition.popularreposapp.persistence.*
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DatabaseModule @Inject constructor(context: Context) {

    private val organizationDao: OrganizationDao = providesOrganizationDao(context)

    private val popRepoDao: PopRepoDao = providesPopRepoDao(context)

    private fun providesOrganizationDao(context: Context): OrganizationDao {
        val db = Room.databaseBuilder(context, RoomDB::class.java, "organizations").build()
        return db.organizationDao()
    }

    private fun providesPopRepoDao(context: Context): PopRepoDao {
        val db = Room.databaseBuilder(context, RoomDB::class.java, "organizations").build()
        return db.popRepoDao()
    }

    @Provides
    @Singleton
    fun providesDatabase(): RepoDataSource {
        return Database(organizationDao, popRepoDao)
    }
}