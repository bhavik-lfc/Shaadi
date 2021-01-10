package com.shaadi.assignment.ui.inbox

import com.shaadi.assignment.data.local.db.typeconverters.InvitationStatus
import com.shaadi.assignment.data.repository.InboxRepository
import com.shaadi.assignment.ui.base.BaseViewModel
import com.shaadi.assignment.utils.Logger
import com.shaadi.assignment.utils.NetworkHelper
import com.shaadi.assignment.utils.SchedulerProvider
import com.shaadi.assignment.utils.Utils
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class InboxViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val inboxRepository: InboxRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {


    companion object {
        const val TAG = "InboxViewModel"
    }

    val inboxUser = inboxRepository.getAll()

    override fun onCreate() {
        getNews()
    }

    private fun getNews() {

        compositeDisposable.add(
            inboxRepository.getUserCount()
                .flatMap {
                    (if (it == 0) {
                        inboxRepository.fetchInboxList("10")
                            .map { result ->
                                return@map Utils.convertList(result)
                            }.flatMap { inboxUsers ->
                                inboxRepository.saveInboxList(inboxUsers)
                            }
                    } else {
                        Single.just(listOf(it))
                    })
                }.subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        Logger.d(TAG, "Users present in db $it")
                    },
                    {
                        Logger.d(TAG, it.toString())
                    }
                )
        )

    }

    fun updateInvitationStatus(invitationStatus: InvitationStatus, id: Long) {
        compositeDisposable.add(
            inboxRepository.setInvitationStatus(invitationStatus, id)
                .subscribeOn(schedulerProvider.io())
                .subscribe()
        )

    }


}