package com.pmd.droidexihibition.popularreposapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pmd.droidexihibition.popularreposapp.persistence.model.GitOrganization
import com.pmd.droidexihibition.popularreposapp.persistence.model.PopRepo

@Database(entities = [GitOrganization::class, PopRepo::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun organizationDao(): OrganizationDao
    abstract fun popRepoDao(): PopRepoDao
}