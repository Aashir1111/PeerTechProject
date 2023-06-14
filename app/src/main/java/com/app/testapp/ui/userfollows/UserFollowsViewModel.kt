package com.app.testapp.ui.userfollows

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.testapp.model.remote.UserModel
import com.app.testapp.network.Failure
import com.app.testapp.network.Repository
import com.app.testapp.network.Success
import com.app.testapp.network.ui
import com.app.testapp.ui.usersearch.UserSearchInterface
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserFollowsViewModel: ViewModel() {
    lateinit var navigation: UserFollowsInterface
    var userDetail = MutableLiveData<UserModel>()

    @OptIn(DelicateCoroutinesApi::class)
    fun call(a: String, Role: String){
        GlobalScope.launch {
            when(val result = Repository.getAllFollows(a,Role)) {
                is Success -> {
                    ui {
                        result.value.let {
                            navigation.userFollowsRes(it)

                        }
                    }

                }
                is Failure -> {
                    ui {
                        println(result.reason.message)
                    }
                }
            }
        }
    }


    @OptIn(DelicateCoroutinesApi::class)
    fun getUserDetailcall(a: String){
        GlobalScope.launch {
            when(val result = Repository.searchUser(a)) {
                is Success -> {
                    ui {
                        result.value.let {
                            userDetail.value = it
                            navigation.openUserDetailSheet()
                        }
                    }

                }
                is Failure -> {
                    ui {
                        println(result.reason.message)
                    }
                }
            }
        }
    }
}