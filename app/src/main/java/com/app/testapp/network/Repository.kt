package com.app.testapp.network

import com.app.testapp.model.Error
import com.app.testapp.model.remote.UserModel
import com.app.testapp.network.api.ApiManager

object Repository {
    suspend fun searchUser(a: String): Result<UserModel,Error> {
        return makeSuspendableRequest { ApiManager.create().getUser(a) }
    }


    suspend fun getAllFollows(a: String, role: String): Result<ArrayList<UserModel>,Error> {
        return makeSuspendableRequest { ApiManager.create().getAllFollows(a,role) }
    }

    }

