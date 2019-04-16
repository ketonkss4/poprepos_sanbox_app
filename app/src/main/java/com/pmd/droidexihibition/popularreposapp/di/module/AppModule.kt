package com.pmd.droidexihibition.popularreposapp.di.module

import android.app.Application
import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pmd.droidexihibition.popularreposapp.BuildConfig
import com.pmd.droidexihibition.popularreposapp.api.GithubApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    fun getAppContext() : Context {
        return application
    }

    @Provides
    @Singleton
    fun providesGithubService(): GithubApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(getOkHttpClient())
            .build()
        return retrofit.create(GithubApiService::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.addInterceptor(loggingInterceptor)
        return httpClientBuilder.build()
    }



    }
