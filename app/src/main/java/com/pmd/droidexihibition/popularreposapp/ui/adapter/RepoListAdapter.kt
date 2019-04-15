package com.pmd.droidexihibition.popularreposapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pmd.droidexihibition.popularreposapp.R
import com.pmd.droidexihibition.popularreposapp.ui.model.PopUiRepo

class RepoListAdapter : RecyclerView.Adapter<PopRepoViewHolder>() {

    private val repoList: ArrayList<PopUiRepo> = ArrayList()

    fun setData(data: List<PopUiRepo>) {
        repoList.clear()
        repoList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PopRepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_list_item, null)
        return PopRepoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repoList.count()
    }

    override fun onBindViewHolder(viewHolder: PopRepoViewHolder, position: Int) {
        val repo = repoList[position]
        viewHolder.bind(repo)
    }

}