package com.shaadi.assignment.data.remote

import com.shaadi.assignment.data.remote.response.InboxResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET(Endpoints.API)
    fun getInboxInviteList(
        @Query("results") resultsCount: String
    ): Single<InboxResponse>

}