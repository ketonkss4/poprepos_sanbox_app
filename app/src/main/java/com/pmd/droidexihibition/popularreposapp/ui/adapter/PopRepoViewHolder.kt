package com.pmd.droidexihibition.popularreposapp.ui.adapter

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.pmd.droidexihibition.popularreposapp.R
import com.pmd.droidexihibition.popularreposapp.databinding.RepoListItemBinding
import com.pmd.droidexihibition.popularreposapp.ui.model.PopUiRepo

class PopRepoViewHolder(
    private val dataBinding: RepoListItemBinding
) : RecyclerView.ViewHolder(dataBinding.root) {
    @BindView(R.id.repo_name)
    lateinit var repoNameView: TextView
    @BindView(R.id.description)
    lateinit var repoDescriptionView: TextView
    @BindView(R.id.stars_counter)
    lateinit var starsCountView: TextView

    fun bind(repo: PopUiRepo) {
        ButterKnife.bind(this, itemView)
        dataBinding.model = repo
        dataBinding.executePendingBindings()
        repoNameView.text = repo.name
        repoDescriptionView.text = repo.description
        starsCountView.text = itemView.resources.getString(R.string.stars_count_text, repo.stars)
    }

}