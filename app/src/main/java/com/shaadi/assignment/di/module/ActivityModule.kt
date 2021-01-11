package com.shaadi.assignment.di.module

import androidx.lifecycle.ViewModelProvider
import com.shaadi.assignment.data.repository.InboxRepository
import com.shaadi.assignment.ui.base.BaseActivity
import com.shaadi.assignment.ui.inbox.InboxViewModel
import com.shaadi.assignment.utils.SchedulerProvider
import com.shaadi.assignment.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity<*, *>) {

    @Provides
    fun provideInboxViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        inboxRepository: InboxRepository
    ): InboxViewModel = ViewModelProvider(
        activity, ViewModelProviderFactory(InboxViewModel::class) {
            InboxViewModel(schedulerProvider, compositeDisposable, inboxRepository)
        }).get(InboxViewModel::class.java)


}