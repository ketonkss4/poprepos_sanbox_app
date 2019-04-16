package com.pmd.droidexihibition.popularreposapp

import android.app.Application
import androidx.fragment.app.Fragment
import com.pmd.droidexihibition.popularreposapp.di.component.DaggerAppComponent
import com.pmd.droidexihibition.popularreposapp.di.module.AppModule
import com.pmd.droidexihibition.popularreposapp.di.module.DatabaseModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class RepoSearchApp : Application(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .databaseModule(DatabaseModule(this))
            .build()
            .inject(this)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

}