package com.pmd.droidexihibition.popularreposapp.repository.model

data class RepoApiModel(
    val id: Int,
    val owner: Owner,
    val name: String,
    val description: String,
    val html_url: String,
    val stargazers_count: Int
)

data class Owner(
    val id: Int
)