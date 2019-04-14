package com.pmd.droidexihibition.popularreposapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.pmd.droidexihibition.popularreposapp.R
import com.pmd.droidexihibition.popularreposapp.databinding.RepoSearchViewBinding

class RepoSearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val searchViewBinding: RepoSearchViewBinding =
            DataBindingUtil.inflate(inflater, R.layout.repo_search_view, container, false)
        searchViewBinding.lifecycleOwner = this
        searchViewBinding.viewModel = ViewModelProviders.of(this).get(RepoSearchViewModel::class.java)
        return searchViewBinding.root
    }

}