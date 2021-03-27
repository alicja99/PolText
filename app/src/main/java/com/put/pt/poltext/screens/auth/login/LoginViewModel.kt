package com.put.pt.poltext.screens.auth.login

import android.app.Application
import androidx.lifecycle.LiveData
import com.google.android.gms.tasks.OnFailureListener
import com.put.pt.poltext.R
import com.put.pt.poltext.common.AuthManager
import com.put.pt.poltext.common.BaseViewModel
import com.put.pt.poltext.common.CommonViewModel
import com.put.pt.poltext.common.SingleLiveEvent


class LoginViewModel(
    private val authManager: AuthManager,
    private val app: Application,
    private val commonViewModel: CommonViewModel,
    onFailureListener: OnFailureListener
) : BaseViewModel(onFailureListener) {

    private val _goToHomeScreen = SingleLiveEvent<Unit>()
    val goToHomeScreen: LiveData<Unit> = _goToHomeScreen
    private val _goToRegisterScreen = SingleLiveEvent<Unit>()
    val goToRegisterScreen: LiveData<Unit> = _goToRegisterScreen

    fun onLoginClick(email: String, password: String) {
        if (validate(email, password)) {
            authManager.login(email, password).addOnSuccessListener {
                _goToHomeScreen.value = Unit
            }.addOnFailureListener(onFailureListener)
        } else {
            commonViewModel.setErrorMessage(app.getString(R.string.enter_name_and_password))
        }
    }

    private fun validate(email: String, password: String) =
        email.isNotEmpty() && password.isNotEmpty()

    fun onRegisterClick() {
        _goToRegisterScreen.call()
    }
}