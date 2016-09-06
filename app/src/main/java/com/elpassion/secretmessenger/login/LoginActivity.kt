package com.elpassion.secretmessenger.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.login.impl.LoginApiProvider

class LoginActivity : AppCompatActivity(), LoginView {

    val controller by lazy { LoginController(LoginApiProvider.get(), this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        val loginInput = findViewById(R.id.login_input) as TextView
        val passwordInput = findViewById(R.id.password_input) as TextView
        findViewById(R.id.login_button).setOnClickListener { controller.onLogin(loginInput.text.trim().toString(), passwordInput.text.trim().toString()) }
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
}