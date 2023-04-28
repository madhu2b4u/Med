package com.demo.med.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.med.LiveDataTestUtil
import com.demo.med.MainCoroutineRule
import com.demo.med.TestUtils
import com.demo.med.common.Status
import com.demo.med.database.source.LocalDataSource
import com.demo.med.home.data.remote.source.HomeRemoteDataSource
import com.demo.med.home.data.repository.HomeRepository
import com.demo.med.home.data.repository.HomeRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class HomeRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repositoryTest: HomeRepository

    @Mock
    lateinit var dataStore: HomeRemoteDataSource

    @Mock
    lateinit var localStore: LocalDataSource

    private val data = TestUtils.healthDataList
    private val drugResponse = TestUtils.response

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        repositoryTest = HomeRepositoryImpl(dataStore, localStore)
    }

    @Test
    fun getDrugsFromAPI() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(dataStore.drugsRequest())
            .thenReturn(drugResponse)

        val result = repositoryTest.drugsRequest(false)
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS)

        assert(LiveDataTestUtil.getValue(result).data == data)
    }

    @Test
    fun getDrugsFromLocalStore() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(localStore.geHealthData())
            .thenReturn(data)

        val result = repositoryTest.drugsRequest(true)
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS)

        assert(LiveDataTestUtil.getValue(result).data == data)
    }

    @Test(expected = Exception::class)
    fun getDrugsFromAPIThrowsException() = mainCoroutineRule.runBlockingTest {
        Mockito.doThrow(Exception("no drugs"))
            .`when`(dataStore.drugsRequest())
        repositoryTest.drugsRequest(false)
    }

}