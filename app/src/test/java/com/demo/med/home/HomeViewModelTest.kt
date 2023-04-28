package com.demo.med.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.demo.med.LiveDataTestUtil
import com.demo.med.MainCoroutineRule
import com.demo.med.TestUtils
import com.demo.med.common.Result
import com.demo.med.common.Status
import com.demo.med.database.entites.HealthData
import com.demo.med.home.domain.HomeUseCase
import com.demo.med.home.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var useCase: HomeUseCase

    private lateinit var viewModel: HomeViewModel

    private val data = TestUtils.healthDataList


    @Before
    fun init() {
        useCase = mock()
    }

    @Test
    fun testGetProblemsListLoadingData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { drugsRequest(false) } doReturn liveData {
                emit(Result.loading())
            }
        }
        viewModel = HomeViewModel(useCase)
        viewModel.getProblemsList(false)
        val result = viewModel.healthData
        result.observeForever { }
        kotlinx.coroutines.delay(2000)
        assert(LiveDataTestUtil.getValue(result).peekContent().status == Status.LOADING)
    }

    @Test
    fun testGetProblemsListSuccessData() = mainCoroutineRule.runBlockingTest {

        useCase = mock {
            onBlocking { drugsRequest(false) } doReturn liveData {
                emit(Result.success(data))
            }
        }

        viewModel = HomeViewModel(useCase)
        viewModel.getProblemsList(false)

        val result = viewModel.healthData
        result.observeForever {}
        kotlinx.coroutines.delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).peekContent().status == Status.SUCCESS &&
                    LiveDataTestUtil.getValue(result).peekContent().data == data
        )
    }

    @Test
    fun testGetProblemsListErrorData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { drugsRequest(false) } doReturn object :
                LiveData<Result<MutableList<HealthData>>>() {
                init {
                    value = Result.error("error")
                }
            }
        }

        viewModel = HomeViewModel(useCase)
        viewModel.getProblemsList(false)

        val result = viewModel.healthData
        result.observeForever {}
        delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).peekContent().status == Status.ERROR &&
                    LiveDataTestUtil.getValue(result).peekContent().message == "error"
        )
    }

}