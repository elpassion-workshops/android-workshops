package com.elpassion.secretmessenger.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.utils.trimmedText
import kotlinx.android.synthetic.main.login_layout.*

class LoginActivity : AppCompatActivity(), Login.View {

    val api: Login.Api = Login.ApiProvider.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        val controller = LoginController(api, this)
        loginButton.setOnClickListener {
            controller.onLogin(loginInput.trimmedText(), passwordInput.text.toString())
        }
    }

    override fun showConversationList() {

    }

    override fun showError() {
    }

    override fun showLoginPasswordError() {
    }
}