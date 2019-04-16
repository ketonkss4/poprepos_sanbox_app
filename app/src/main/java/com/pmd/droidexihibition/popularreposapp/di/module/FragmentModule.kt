package com.pmd.droidexihibition.popularreposapp.di.module

import com.pmd.droidexihibition.popularreposapp.ui.RepoSearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributeRepoSearchFragment(): RepoSearchFragment
}