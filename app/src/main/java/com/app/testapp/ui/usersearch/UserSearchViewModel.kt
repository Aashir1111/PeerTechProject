package com.app.testapp.ui.usersearch

import android.view.KeyEvent
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.testapp.model.remote.UserModel
import com.app.testapp.network.Failure
import com.app.testapp.network.Repository
import com.app.testapp.network.Success
import com.app.testapp.network.ui
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class UserSearchViewModel: ViewModel() {

    lateinit var navigation: UserSearchInterface
    var loading = MutableLiveData<Boolean>(false)
    var alpha = MutableLiveData<Float>(0f)
    var datafound = MutableLiveData<Boolean>(false)
    var searchedUser = MutableLiveData<UserModel>()
    var followers = "followers"
    var following = "following"

    fun SearchUser(){
        navigation.requestSearchUser()
    }

    fun gotoNext(role: String){
        navigation.goToNextScreen(role)

    }

    @OptIn(DelicateCoroutinesApi::class)
    fun call(a: String){
        loading.value = true
        alpha.value = 0f
        GlobalScope.launch {
            when(val result = Repository.searchUser(a)) {
                is Success -> {
                    ui {
                        result.value.let {
                            searchedUser.value = it
                        }
                        datafound.value = true
                        loading.value = false
                        alpha.value = 0f
                    }

                }
                is Failure -> {
                    ui {
                        datafound.value = false
                        loading.value = false
                        alpha.value = 1f
                    }
                }
            }
        }
    }


    fun onEditorAction(view: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        call(view?.text.toString())
        return true
    }





}