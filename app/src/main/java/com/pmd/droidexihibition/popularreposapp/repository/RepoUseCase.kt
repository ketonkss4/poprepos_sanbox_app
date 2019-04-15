package com.pmd.droidexihibition.popularreposapp.repository

interface RepoUseCase {
    suspend fun performAsync(searchText: String)
}