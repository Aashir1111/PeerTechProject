package com.app.testapp.ui.userfollows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.testapp.BR
import com.app.testapp.common.parcelable
import com.app.testapp.databinding.FragmentFollowsDetailBinding
import com.app.testapp.databinding.FragmentUserFollowersBinding
import com.app.testapp.model.IntentData
import com.app.testapp.model.remote.UserModel
import com.app.testapp.ui.usersearch.UserSearchInterface
import com.app.testapp.ui.usersearch.UserSearchViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog


class UserFollowsScreen : Fragment(), UserFollowsInterface {
    private fun provideViewModel(): UserFollowsViewModel =
        ViewModelProvider(this)[UserFollowsViewModel::class.java]
    private fun provideNav(): UserFollowsInterface = this

    private lateinit var viewDataBinding: FragmentUserFollowersBinding
    private lateinit var followsAdapter: FollowerAdapter
    private lateinit var intentData: IntentData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = FragmentUserFollowersBinding.inflate(inflater, container, false)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.setVariable(BR.vm, provideViewModel().apply { navigation = provideNav()})
        viewDataBinding.executePendingBindings()
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followsAdapter = FollowerAdapter()
        followsAdapter.listener = provideNav()
        viewDataBinding.recyce.apply {
            layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
           adapter = followsAdapter
        }


        if (arguments != null) {
            intentData = arguments?.parcelable("USER")!!
            provideViewModel().call(intentData.username,intentData.action)
        }


    }

    override fun userFollowsRes(res: ArrayList<UserModel>) {
        followsAdapter.setItem(res)
    }

    override fun onUserClick(res: UserModel) {
        provideViewModel().getUserDetailcall(res.login?: "")
    }

    override fun openUserDetailSheet() {
        showCodeSheet()
    }


    private fun showCodeSheet() {
        activity?.let {
            val dialogSheet = BottomSheetDialog(it)
            val view = FragmentFollowsDetailBinding.inflate(layoutInflater)
            view.lifecycleOwner = this
            view.vm = provideViewModel()
            dialogSheet.setContentView(view.root)
            dialogSheet.show()
        }
    }

}

