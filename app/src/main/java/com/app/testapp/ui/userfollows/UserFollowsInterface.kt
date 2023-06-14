package com.app.testapp.ui.userfollows

import com.app.testapp.model.remote.UserModel

interface UserFollowsInterface {
    fun userFollowsRes(res: ArrayList<UserModel>)
    fun onUserClick(res: UserModel)
    fun openUserDetailSheet()
}