package com.elpassion.secretmessenger.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.conversation.list.ConversationListActivity
import com.jakewharton.rxbinding.view.clicks
import com.jakewharton.rxbinding.widget.textChanges
import kotlinx.android.synthetic.main.login_layout.*

class LoginActivity : AppCompatActivity(), Login.View {

    val api: Login.Api = Login.ApiProvider.get()
    val controller = LoginController(api, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        controller.onCreate()
    }

    override fun showConversationList() {
        ConversationListActivity.start(this)
    }

    override fun showError() {
    }

    override fun showLoginPasswordError() {
    }

    override fun loginClicks() = loginButton.clicks()
    override fun loginInputChanges() = loginInput.textChanges().map { it.toString() }
    override fun passwordInputChanges() = passwordInput.textChanges().map { it.toString() }
    override fun setStatus(status: String) { title = status }
    override val login: String get() = loginInput.text.toString()
    override val password: String get() = passwordInput.text.toString()
}