package com.pmd.droidexihibition.popularreposapp.persistence.model

import androidx.room.Embedded
import androidx.room.Relation

class OrganizationWithRepos {
    @Embedded
    lateinit var organization: GitOrganization

    @Relation(parentColumn = "id", entityColumn = "orgId", entity = PopRepo::class)
    lateinit var popRepos: List<PopRepo>
}