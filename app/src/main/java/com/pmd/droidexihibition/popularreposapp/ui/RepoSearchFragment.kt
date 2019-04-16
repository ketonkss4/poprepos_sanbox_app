package com.pmd.droidexihibition.popularreposapp.ui

import android.content.Context
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
import com.pmd.droidexihibition.popularreposapp.R
import com.pmd.droidexihibition.popularreposapp.databinding.RepoSearchViewBinding
import com.pmd.droidexihibition.popularreposapp.ui.adapter.RepoListAdapter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RepoSearchFragment : Fragment() {

    @BindView(R.id.recyclerView)
    lateinit var repoListView: RecyclerView
    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val searchViewBinding: RepoSearchViewBinding =
            DataBindingUtil.inflate(inflater, R.layout.repo_search_view, container, false)
        searchViewBinding.lifecycleOwner = this
        ButterKnife.bind(this, searchViewBinding.root)
        ButterKnife.setDebug(true)

        val layoutManager = LinearLayoutManager(context)
        repoListView.layoutManager = layoutManager
        val repoListAdapter = RepoListAdapter()
        repoListView.adapter = repoListAdapter
        repoListView.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))

        val searchViewModel = ViewModelProviders.of(this, viewModelFactory).get(RepoSearchViewModel::class.java)
        searchViewModel.repoViewLiveData.observe(this, Observer {
            repoListAdapter.setData(it)
        })

        searchViewBinding.viewModel = searchViewModel
        return searchViewBinding.root
    }

}