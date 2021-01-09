package com.shaadi.assignment.ui.inbox

import com.shaadi.assignment.ui.base.BaseViewModel
import com.shaadi.assignment.utils.NetworkHelper
import com.shaadi.assignment.utils.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable

class InboxViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    override fun onCreate() {

    }


}