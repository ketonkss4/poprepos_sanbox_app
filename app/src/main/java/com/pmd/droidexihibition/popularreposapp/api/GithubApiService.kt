package com.pmd.droidexihibition.popularreposapp.api

import com.pmd.droidexihibition.popularreposapp.persistence.model.PopRepo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {
    @GET("orgs/{org}/repos?type=public?sort=updated?direction=desc")
    fun searchPublicReposByOrg(@Path("org") searchText: String) : Deferred<List<PopRepo>>
}
