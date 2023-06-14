package com.app.testapp.network.api

import com.app.testapp.model.remote.UserModel
import com.app.testapp.network.api.ApiConstants.FOLLOWS
import com.app.testapp.network.api.ApiConstants.USER
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*


interface ApiCalls {

    @GET(USER)
    fun getUser(
        @Path("name") n : String
    ): Deferred<Response<UserModel>>

    @GET(FOLLOWS)
    fun getAllFollows(
        @Path("name") n : String,
        @Path("role") r : String
    ): Deferred<Response<ArrayList<UserModel>>>


}


