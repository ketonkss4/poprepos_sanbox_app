package com.pmd.droidexihibition.popularreposapp.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.pmd.droidexihibition.popularreposapp.R
import com.pmd.droidexihibition.popularreposapp.ui.model.PopUiRepo

class PopRepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    @BindView(R.id.repo_name) lateinit var repoNameView: TextView
    @BindView(R.id.description) lateinit var repoDescriptionView: TextView
    @BindView(R.id.stars_counter) lateinit var starsCountView: TextView

    fun bind(repo: PopUiRepo) {
        ButterKnife.bind(this, itemView)
        repoNameView.text = repo.name
        repoDescriptionView.text = repo.description
        starsCountView.text = repo.stars.toString()
    }

}