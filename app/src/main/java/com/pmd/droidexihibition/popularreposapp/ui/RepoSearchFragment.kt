package com.pmd.droidexihibition.popularreposapp.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.snackbar.Snackbar
import com.pmd.droidexihibition.popularreposapp.R
import com.pmd.droidexihibition.popularreposapp.databinding.RepoSearchViewBinding
import com.pmd.droidexihibition.popularreposapp.ui.adapter.RepoListAdapter
import com.pmd.droidexihibition.popularreposapp.ui.webview.RepoWebViewer
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

const val SAVE_STATE = "SAVE_STATE"

interface RepoActionListener {
    fun onRepoItemClick(repoLink: String)
}

class RepoSearchFragment : Fragment(), RepoActionListener {

    @BindView(R.id.repo_list_view)
    lateinit var repoListView: RecyclerView
    @BindView(R.id.search_view)
    lateinit var searchView: EditText
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var webView: RepoWebViewer
    lateinit var viewModel: RepoSearchViewModel
    private var savedState: Bundle = Bundle()
    private val compositeDisposable = CompositeDisposable()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val searchViewBinding: RepoSearchViewBinding =
            DataBindingUtil.inflate(
                inflater,
                com.pmd.droidexihibition.popularreposapp.R.layout.repo_search_view,
                container,
                false
            )
        searchViewBinding.lifecycleOwner = this
        ButterKnife.bind(this, searchViewBinding.root)
        ButterKnife.setDebug(true)

        val layoutManager = LinearLayoutManager(context)

        val repoListAdapter = RepoListAdapter(this)
        repoListView.adapter = repoListAdapter
        repoListView.hasFixedSize()
        repoListView.layoutManager = layoutManager
        repoListView.adapter = repoListAdapter
        repoListView.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RepoSearchViewModel::class.java)
        viewModel.repoViewLiveData.observe(this, Observer {
            repoListAdapter.setData(it)
        })
        searchViewBinding.viewModel = viewModel
        compositeDisposable.add(viewModel.errorObservable.observeOn(AndroidSchedulers.mainThread()).subscribe {
            Snackbar.make(searchViewBinding.root, it.message, Snackbar.LENGTH_SHORT).show()
        })
        return searchViewBinding.root
    }

    //TODO still need to figure out saving state with navigation architecture currently
    //TODO state is lost by onResume
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let { instanceState ->
            instanceState.getBundle(SAVE_STATE)?.let {
                savedState = it
                viewModel.restoreInstanceState(it)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        saveSearchState()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun saveSearchState() {
        val currentInput = searchView.text.toString()
        savedState.putString(CURRENT_SEARCH_TEXT, currentInput)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBundle(SAVE_STATE, savedState)
        super.onSaveInstanceState(outState)
    }

    override fun onRepoItemClick(repoLink: String) {
        activity?.let {
            webView.showInWebView(it, Uri.parse(repoLink))
        }
    }
}