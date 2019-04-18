package com.pmd.droidexihibition.popularreposapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.MutableLiveData
import com.pmd.droidexihibition.popularreposapp.persistence.RepoDataSource
import com.pmd.droidexihibition.popularreposapp.persistence.model.GitOrganization
import com.pmd.droidexihibition.popularreposapp.persistence.model.OrganizationWithRepos
import com.pmd.droidexihibition.popularreposapp.persistence.model.PopRepo
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class RepositoryTest {

    val dataSource = mockk<RepoDataSource>(relaxed = true)
    val repoUseCase = mockk<SearchRepoUseCase>(relaxed = true)
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun searchExistingOrganizationTest() {
        val testSearch = "test_search"
        val mockRepoList = mockk<List<PopRepo>>()
        val organizationWithRepos = OrganizationWithRepos()
        val lifecycleRegistry = LifecycleRegistry(mockkClass(LifecycleOwner::class))

        organizationWithRepos.organization = GitOrganization(0, testSearch)
        organizationWithRepos.popRepos = mockRepoList

        val organizationLiveData: MutableLiveData<List<OrganizationWithRepos>> = MutableLiveData()
        every { dataSource.getAllOrganizationsWithRepos() } returns organizationLiveData
        val repository = Repository(dataSource, repoUseCase)


        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        val repoLiveData = repository.getRepoLiveData(testSearch)
        repoLiveData.observe({ lifecycleRegistry }) {}
        organizationLiveData.postValue(
            arrayListOf(
                organizationWithRepos
            )
        )
        Assert.assertEquals(mockRepoList, repoLiveData.value)
    }

    @Test
    fun searchNonExistingOrganizationTest() {
        val testSearch = "test_search"
        val lifecycleRegistry = LifecycleRegistry(mockkClass(LifecycleOwner::class))

        val organizationLiveData: MutableLiveData<List<OrganizationWithRepos>> = MutableLiveData()
        every { dataSource.getAllOrganizationsWithRepos() } returns organizationLiveData
        val repository = Repository(dataSource, repoUseCase)


        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        val repoLiveData = repository.getRepoLiveData(testSearch)
        repoLiveData.observe({ lifecycleRegistry }) {}
        organizationLiveData.postValue(emptyList())
        Assert.assertTrue(repoLiveData.value!!.isEmpty())
    }
}