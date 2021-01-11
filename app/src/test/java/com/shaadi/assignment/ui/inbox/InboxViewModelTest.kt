package com.shaadi.assignment.ui.inbox

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.shaadi.assignment.data.local.db.entity.InboxUser
import com.shaadi.assignment.data.remote.response.Result
import com.shaadi.assignment.data.repository.InboxRepository
import com.shaadi.assignment.utils.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class InboxViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var testScheduler: TestScheduler

    @Mock
    private lateinit var inboxRepository: InboxRepository

    @Mock
    private lateinit var progressLoadingObserver: Observer<Boolean>

    @Mock
    private lateinit var errorStatusObserver: Observer<Boolean>

    @Mock
    private lateinit var inboxUserObserver: Observer<List<InboxUser>>

    private lateinit var inboxViewModel: InboxViewModel

    @Before
    fun setUp() {
        val compositeDisposable = CompositeDisposable()
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)

        Mockito.doReturn(MutableLiveData<List<InboxUser>>())
            .`when`(inboxRepository)
            .getAll()

        inboxViewModel = InboxViewModel(
            testSchedulerProvider,
            compositeDisposable,
            inboxRepository
        )
        inboxViewModel.progressLoading.observeForever(progressLoadingObserver)
        inboxViewModel.errorStatus.observeForever(errorStatusObserver)
        inboxViewModel.inboxUser.observeForever(inboxUserObserver)
    }

    @After
    fun tearDown() {
        inboxViewModel.progressLoading.removeObserver(progressLoadingObserver)
        inboxViewModel.errorStatus.removeObserver(errorStatusObserver)
        inboxViewModel.inboxUser.removeObserver(inboxUserObserver)
    }

    @Test
    fun whenUserCountZero_OnSuccess_ShouldSaveInDb() {
        Mockito.doReturn(Single.just(0))
            .`when`(inboxRepository)
            .getUserCount()

        Mockito.doReturn(Single.just(listOf<Result>()))
            .`when`(inboxRepository)
            .fetchInboxList(Mockito.anyString())

        Mockito.doReturn(Single.just(listOf<Long>()))
            .`when`(inboxRepository)
            .saveInboxList(Mockito.anyList())

        inboxViewModel.onCreate()
        testScheduler.triggerActions()

        Mockito.verify(inboxRepository, times(1))
            .fetchInboxList(Mockito.anyString())

        Mockito.verify(inboxRepository, times(1))
            .saveInboxList(Mockito.anyList())

        assert(inboxViewModel.progressLoading.value == false)
        Mockito.verify(progressLoadingObserver).onChanged(true)
        Mockito.verify(progressLoadingObserver).onChanged(false)
    }

    @Test
    fun whenUserCountNonZero_ShouldNotFetchFromServer() {
        Mockito.doReturn(Single.just(10))
            .`when`(inboxRepository)
            .getUserCount()

        inboxViewModel.onCreate()
        testScheduler.triggerActions()

        Mockito.verify(inboxRepository, times(0))
            .fetchInboxList(Mockito.anyString())

        Mockito.verify(inboxRepository, times(0))
            .saveInboxList(Mockito.anyList())

        assert(inboxViewModel.progressLoading.value == false)
        Mockito.verify(progressLoadingObserver).onChanged(true)
        Mockito.verify(progressLoadingObserver).onChanged(false)
    }

    @Test
    fun whenUserCountZero_OnError_ShouldShowErrorState() {
        Mockito.doReturn(Single.just(0))
            .`when`(inboxRepository)
            .getUserCount()

        Mockito.`when`(inboxRepository.fetchInboxList(Mockito.anyString()))
            .thenReturn(Single.error(Throwable("An error has occurred!")))

        inboxViewModel.onCreate()
        testScheduler.triggerActions()

        Mockito.verify(inboxRepository, times(1))
            .fetchInboxList(Mockito.anyString())

        Mockito.verify(inboxRepository, times(0))
            .saveInboxList(Mockito.anyList())

        assert(inboxViewModel.progressLoading.value == false)
        assert(inboxViewModel.inboxUser.value == null)
        Mockito.verify(progressLoadingObserver).onChanged(true)
        Mockito.verify(progressLoadingObserver).onChanged(false)
        Mockito.verify(errorStatusObserver).onChanged(false)
        Mockito.verify(errorStatusObserver).onChanged(true)
    }

}