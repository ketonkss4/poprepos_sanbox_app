package com.pmd.droidexihibition.popularreposapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pmd.droidexihibition.popularreposapp.R
import com.pmd.droidexihibition.popularreposapp.databinding.RepoListItemBinding
import com.pmd.droidexihibition.popularreposapp.ui.RepoActionListener
import com.pmd.droidexihibition.popularreposapp.ui.model.PopUiRepo

class RepoListAdapter(private val actionListener: RepoActionListener) : RecyclerView.Adapter<PopRepoViewHolder>() {

    private val repoList: ArrayList<PopUiRepo> = ArrayList()

    fun setData(data: List<PopUiRepo>) {
        repoList.clear()
        repoList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PopRepoViewHolder {
        val binding: RepoListItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.repo_list_item, parent, false)
        binding.repoListActionListener = actionListener
        return PopRepoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return repoList.count()
    }

    override fun onBindViewHolder(viewHolder: PopRepoViewHolder, position: Int) {
        val repo = repoList[position]
        viewHolder.bind(repo)
    }

}