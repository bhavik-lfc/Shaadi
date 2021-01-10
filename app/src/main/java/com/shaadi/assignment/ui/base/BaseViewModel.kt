package com.shaadi.assignment.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaadi.assignment.R
import com.shaadi.assignment.utils.NetworkHelper
import com.shaadi.assignment.utils.Resource
import com.shaadi.assignment.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel(
    protected val schedulerProvider: SchedulerProvider,
    protected val compositeDisposable: CompositeDisposable,
    protected val networkHelper: NetworkHelper
) : ViewModel() {

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()
    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()

    protected fun checkInternetConnectionWithMessage(): Boolean =
        if (networkHelper.isNetworkAvailable()) {
            true
        } else {
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
            false
        }


    protected fun checkInternetConnection(): Boolean = networkHelper.isNetworkAvailable()

    abstract fun onCreate()

}