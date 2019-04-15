package com.pmd.droidexihibition.popularreposapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.pmd.droidexihibition.popularreposapp.concurrency.AppDispatchers
import com.pmd.droidexihibition.popularreposapp.concurrency.CoroutineScopedViewModel
import com.pmd.droidexihibition.popularreposapp.persistence.model.PopRepo
import com.pmd.droidexihibition.popularreposapp.repository.Repository
import com.pmd.droidexihibition.popularreposapp.ui.model.PopUiRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepoSearchViewModel @Inject constructor(
    private val repository: Repository,
    override val dispatchers: AppDispatchers
) : CoroutineScopedViewModel() {

    private lateinit var searchInput: String

    private lateinit var currentSearchLiveData: LiveData<List<PopRepo>>

    val repoList: LiveData<List<PopUiRepo>> =
        Transformations.switchMap(currentSearchLiveData) { searchResults ->
            val repoList = searchResults.map {
                PopUiRepo(
                    it.name,
                    it.html_url,
                    it.description,
                    it.stargazers_count
                )
            }
            val mutableLiveData = MutableLiveData<List<PopUiRepo>>()
            mutableLiveData.value = repoList
            mutableLiveData
        }

    fun onUpdateSearchInput(textInput: String) {
        searchInput = textInput
    }

    fun onSearchClick() {
        if (searchInput.isBlank()) return
        launch {
            currentSearchLiveData = repository.getRepositories(searchInput)

        }
    }

}