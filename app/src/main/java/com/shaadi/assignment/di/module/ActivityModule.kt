package com.shaadi.assignment.di.module

import androidx.lifecycle.ViewModelProviders
import com.shaadi.assignment.ui.base.BaseActivity
import com.shaadi.assignment.ui.inbox.InboxViewModel
import com.shaadi.assignment.utils.NetworkHelper
import com.shaadi.assignment.utils.SchedulerProvider
import com.shaadi.assignment.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity<*, *>) {

    @Provides
    fun provideInboxViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): InboxViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(InboxViewModel::class) {
            InboxViewModel(schedulerProvider, compositeDisposable, networkHelper)
        }).get(InboxViewModel::class.java)


}