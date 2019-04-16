package com.pmd.droidexihibition.popularreposapp.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Organizations")
data class GitOrganization(
    @PrimaryKey
    var id: Int,
    var name: String
)