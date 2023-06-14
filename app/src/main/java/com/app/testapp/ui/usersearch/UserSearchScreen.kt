package com.app.testapp.ui.usersearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.testapp.BR
import com.app.testapp.R
import com.app.testapp.databinding.FragmentUserSearchScreenBinding
import com.app.testapp.model.IntentData

class UserSearchScreen : Fragment(), UserSearchInterface {
    private fun provideViewModel(): UserSearchViewModel = ViewModelProvider(this)[UserSearchViewModel::class.java]
    private fun provideNav(): UserSearchInterface  = this
    private lateinit var viewDataBinding: FragmentUserSearchScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = FragmentUserSearchScreenBinding.inflate(inflater,container,false)
        viewDataBinding.vm = provideViewModel()
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.setVariable(BR.vm, provideViewModel().apply { navigation = provideNav()})
        viewDataBinding.executePendingBindings()
        return viewDataBinding.root
    }

    override fun requestSearchUser() {
        provideViewModel().call(viewDataBinding.searchEditText.text.toString())
    }

    override fun goToNextScreen(role: String) {
        val bundle = Bundle()
        bundle.putParcelable("USER", IntentData(provideViewModel().searchedUser.value?.login?: "",role))
        findNavController().navigate(R.id.actionFollowsFragment, bundle)

    }


}