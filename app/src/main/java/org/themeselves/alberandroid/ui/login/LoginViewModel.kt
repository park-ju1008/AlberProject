package org.themeselves.alberandroid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.themeselves.alberandroid.base.BaseViewModel
import org.themeselves.alberandroid.data.source.UserRepository
import org.themeselves.alberandroid.util.Event
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
):BaseViewModel(){

    private val _loginType = MutableLiveData<Event<LoginType>>()
    val loginType:LiveData<Event<LoginType>> = _loginType


    fun showLoginPage(loginType: LoginType){
        _loginType.value=Event(loginType)
    }
}