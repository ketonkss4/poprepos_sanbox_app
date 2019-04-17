package com.pmd.droidexihibition.popularreposapp.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.pmd.droidexihibition.popularreposapp.databinding.RepoSearchViewBinding
import com.pmd.droidexihibition.popularreposapp.ui.adapter.RepoListAdapter
import com.pmd.droidexihibition.popularreposapp.ui.webview.RepoWebViewer
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


interface RepoActionListener {
    fun onRepoItemClick(repoLink: String)
}

class RepoSearchFragment : Fragment(), RepoActionListener {

    @BindView(com.pmd.droidexihibition.popularreposapp.R.id.recyclerView)
    lateinit var repoListView: RecyclerView
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var webView: RepoWebViewer


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
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

        val searchViewModel = ViewModelProviders.of(this, viewModelFactory).get(RepoSearchViewModel::class.java)
        searchViewModel.repoViewLiveData.observe(this, Observer {
            repoListAdapter.setData(it)
        })
        searchViewBinding.viewModel = searchViewModel
        return searchViewBinding.root
    }

    override fun onRepoItemClick(repoLink: String) {
        activity?.let {
            webView.showInWebView(it.applicationContext, Uri.parse(repoLink))
        }
    }
}