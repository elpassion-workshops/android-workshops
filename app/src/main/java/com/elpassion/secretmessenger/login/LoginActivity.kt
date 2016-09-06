package com.elpassion.secretmessenger.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.login.impl.LoginApiProvider
import com.elpassion.secretmessenger.utils.trimmedText
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : AppCompatActivity(), LoginView {
    val controller by lazy { LoginController(LoginApiProvider.get(), this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        loginButton.setOnClickListener { controller.onLogin(loginInput.trimmedText(), passwordInput.trimmedText()) }
    }

    override fun onDestroy() {
        controller.onDestroy()
        super.onDestroy()
    }

    override fun openHomeScreen() {

    }

    override fun showLoginError() {

    }

    override fun showLoginDataIncorrectError() {

    }

    override fun showLoader() {

    }

    override fun dismissLoader() {

    }

    override fun showRegisterScreen() {

    }
}
