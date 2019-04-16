package com.pmd.droidexihibition.popularreposapp.api

import com.pmd.droidexihibition.popularreposapp.repository.model.RepoApiModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {
    @GET("orgs/{org}/repos?type=public?sort=updated?direction=desc")
    fun searchPublicReposByOrgAsync(@Path("org") searchText: String) : Deferred<List<RepoApiModel>>
}
