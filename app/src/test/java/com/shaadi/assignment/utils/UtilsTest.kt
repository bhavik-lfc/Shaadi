package com.shaadi.assignment.utils

import com.shaadi.assignment.data.local.db.entity.InboxUser
import com.shaadi.assignment.data.local.db.typeconverters.InvitationStatus
import com.shaadi.assignment.data.remote.response.*
import org.junit.Test


class UtilsTest {


    @Test
    fun integrationMapperTest() {

        val resultList = mutableListOf<Result>().apply {
            this.add(
                Result(
                    "",
                    Dob(30, ""),
                    "TEST_EMAIL",
                    "MALE",
                    Id("", ""),
                    Location(
                        "TEST_CITY", Coordinates("", ""), "TEST_COUNTRY", "", "TEST_STATE",
                        Street("", 0), Timezone("", "")
                    ),
                    Login("", "", "", "", "", "", ""),
                    Name("FIRST_NAME", "LAST_NAME", "TITLE"),
                    "",
                    "",
                    Picture("TEST_PICTURE", "", ""),
                    Registered(1, "")
                )
            )
        }


        val inboxList: List<InboxUser> = Utils.convertList(resultList)

        assert(inboxList[0].name == "FIRST_NAME L")
        assert(inboxList[0].age == 30)
        assert(inboxList[0].imgUrl == "TEST_PICTURE")
        assert(inboxList[0].location == "TEST_STATE, TEST_COUNTRY")
        assert(inboxList[0].invitation_status == InvitationStatus.PENDING)

    }

}