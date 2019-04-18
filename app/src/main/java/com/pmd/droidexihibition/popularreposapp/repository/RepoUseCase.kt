package com.pmd.droidexihibition.popularreposapp.repository

import com.pmd.droidexihibition.popularreposapp.ui.model.Result

interface RepoUseCase {
    suspend fun performAsync(searchText: String) : Result<Boolean>
}