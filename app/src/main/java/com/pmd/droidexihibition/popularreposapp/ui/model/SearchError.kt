package com.pmd.droidexihibition.popularreposapp.ui.model

sealed class Result<out T : Any>
class Success<out T : Any>(val data: T) : Result<T>()
class SearchError(val exception: Throwable, val message: String = exception.localizedMessage): Result<Nothing>()
