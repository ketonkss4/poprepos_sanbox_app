package com.pmd.droidexihibition.popularreposapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pmd.droidexihibition.popularreposapp.repository.Repository
import javax.inject.Inject

class RepoSearchViewModel @Inject constructor(
    val repository : Repository
) : ViewModel() {

    private val searchInputLiveData: MutableLiveData<String> = MutableLiveData()

    fun onUpdateSearchInput(textInput: String) {
        searchInputLiveData.value = textInput
    }

    fun onSearchClick() {
    }
}