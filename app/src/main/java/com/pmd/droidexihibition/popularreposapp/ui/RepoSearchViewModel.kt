package com.pmd.droidexihibition.popularreposapp.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.pmd.droidexihibition.popularreposapp.concurrency.AppDispatchers
import com.pmd.droidexihibition.popularreposapp.concurrency.CoroutineScopedViewModel
import com.pmd.droidexihibition.popularreposapp.persistence.model.PopRepo
import com.pmd.droidexihibition.popularreposapp.repository.Repository
import com.pmd.droidexihibition.popularreposapp.ui.model.PopUiRepo
import com.pmd.droidexihibition.popularreposapp.ui.model.SearchError
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


const val CURRENT_SEARCH_TEXT = "CURRENT_SEARCH_TEXT"

/**
 * Keeps the view state of the search view and update the ui via databinding
 * and live data subscription
 */
class RepoSearchViewModel @Inject constructor(
    private val repository: Repository,
    override val dispatchers: AppDispatchers
) : CoroutineScopedViewModel() {

    private var searchInput: MutableLiveData<String> = MutableLiveData()
    val errorObservable = PublishSubject.create<SearchError>()
    val isInProgressObservable = ObservableInt(View.GONE)

    private val repoList: LiveData<List<PopRepo>?> =
        Transformations.switchMap(searchInput) { searchText ->
            repository.getRepoLiveData(searchText.toLowerCase())
        }

    val repoViewLiveData: LiveData<List<PopUiRepo>> = Transformations.switchMap(repoList) { searchResults ->
        val repoList = searchResults?.map {
            PopUiRepo(
                it.name,
                it.html_url,
                it.description,
                it.stargazers_count
            )
        }?.sortedByDescending { it.stars } ?: emptyList()
        val mutableLiveData = MutableLiveData<List<PopUiRepo>>()
        mutableLiveData.value = repoList
        mutableLiveData
    }

    fun onUpdateSearchInput(textInput: String) {
        if (textInput != searchInput.value) searchInput.value = textInput
    }

    /**
     * Databinding method initiates an api request on search input upon
     * explicit user request
     * this is to limit excessive network use.
     */
    fun onSearchClick() {
        //TODO cancel current search if new one requested before previous is complete
        searchInput.value?.let { searchText ->
            isInProgressObservable.set(View.VISIBLE)
            launch(dispatchers.ioDispatcher()) {
                val result = repository.searchReposAsync(searchText.toLowerCase())
                when (result) {
                    is SearchError -> {
                        errorObservable.onNext(result)
                    }
                }
                withContext(dispatchers.uiDispatcher()) {
                    isInProgressObservable.set(View.GONE)
                }
            }
        }
    }

    // view model should keep its own state between orientation changes
    // however if the application is killed by the system, state will need
    // to be restored using the saved instance state
    fun restoreInstanceState(savedInstanceState: Bundle?) {
        val savedSearch = savedInstanceState?.getString(CURRENT_SEARCH_TEXT)
        savedSearch?.let {
            if (it != searchInput.value) searchInput.value = it
        }
    }

}