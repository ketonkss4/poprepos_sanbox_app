package com.pmd.droidexihibition.popularreposapp.persistence.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GitOrganization(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val name: String,
    val popularRepos : List<PopRepo>
)