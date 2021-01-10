package com.shaadi.assignment.utils

import com.shaadi.assignment.data.local.db.entity.InboxUser
import com.shaadi.assignment.data.local.db.typeconverters.InvitationStatus
import com.shaadi.assignment.data.remote.response.Result

object Utils {


    fun convertList(resultList: List<Result>): List<InboxUser> {

        val inboxUserList = mutableListOf<InboxUser>()

        resultList.forEach {
            inboxUserList.add(
                InboxUser(
                    0,
                    it.name.first + " " + it.name.last[0],
                    it.dob.age,
                    it.picture.large,
                    it.location.state + ", " + it.location.country,
                    InvitationStatus.PENDING
                )
            )
        }

        return inboxUserList
    }

}