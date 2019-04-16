package com.pmd.droidexihibition.popularreposapp.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PopularRepositories")
data class PopRepo(
    @PrimaryKey
    val id: Int,
    val orgId: Int,
    val name: String,
    val description: String,
    val html_url: String,
    val stargazers_count: Int
)