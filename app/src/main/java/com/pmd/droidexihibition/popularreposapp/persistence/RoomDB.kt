package com.pmd.droidexihibition.popularreposapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pmd.droidexihibition.popularreposapp.persistence.model.GitOrganization

@Database(entities = [GitOrganization::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun organizationDao(): GitOrganization
}