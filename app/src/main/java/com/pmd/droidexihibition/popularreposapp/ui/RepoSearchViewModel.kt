package com.pmd.droidexihibition.popularreposapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class RepoSearchViewModel @Inject constructor(
//    val repository : Repository
) : ViewModel() {

    private val searchInputLiveData: MutableLiveData<String> = MutableLiveData()
//    val repoListLiveData: LiveData<List<PopRepo>> =
//        Transformations.switchMap(searchInputLiveData) { searchInput ->
//            repository.getRepositiories(searchInput)
//        }

    fun onUpdateSearchInput(textInput: String) {
        searchInputLiveData.value = textInput
    }

    fun onSearchClick() {
    }
}