package com.shaadi.assignment.data.repository

import androidx.lifecycle.LiveData
import com.shaadi.assignment.data.local.db.DatabaseService
import com.shaadi.assignment.data.local.db.entity.InboxUser
import com.shaadi.assignment.data.local.db.typeconverters.InvitationStatus
import com.shaadi.assignment.data.remote.NetworkService
import com.shaadi.assignment.data.remote.response.Result
import io.reactivex.Single
import javax.inject.Inject

class InboxRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun fetchInboxList(count: String): Single<List<Result>> =
        networkService.getInboxInviteList(count).map {
            it.results
        }

    fun saveInboxList(inboxList: List<InboxUser>): Single<List<Long>> {
        return databaseService.inboxUserDao().insert(inboxList)
    }

    fun getAll(): LiveData<List<InboxUser>> =
        databaseService.inboxUserDao().getAll()


    fun getUserCount(): Single<Int> = databaseService.inboxUserDao().count()

    fun setInvitationStatus(invitationStatus: InvitationStatus, id: Long) =
        databaseService.inboxUserDao().updateInvitationStatus(invitationStatus, id)

}
