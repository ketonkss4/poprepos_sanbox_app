package com.pmd.droidexihibition.popularreposapp.persistence.model

data class PopRepo(
    val id : Int,
    val name: String,
    val html_url: String,
    val stargazers_count : Int
)