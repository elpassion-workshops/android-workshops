package com.elpassion.secretmessenger.register

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.conversation.list.ConversationListActivity
import com.elpassion.secretmessenger.utils.trimmedText
import kotlinx.android.synthetic.main.register_layout.*

class RegisterActivity : AppCompatActivity(), Register.View {


    val api: Register.Api = Register.ApiProvider.get()
    val controller = RegisterController(api, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)
        controller.onCreate()
    }

    override fun showErrorPasswordsDontMatch() {
    }

    override fun showErrorEmptyPassword() {
    }

    override fun showErrorEmptyLogin() {
        loginInputLayout.error = getString(R.string.login_empty_error)
    }

    override fun showErrorRegistrationFail() {
    }

    override fun init() {
        registerButton.setOnClickListener {
            controller.onRegister(loginInput.trimmedText(), passwordInput.text.toString(), repeatedPasswordInput.text.toString())
        }
    }
    override fun showConversationList() {
        ConversationListActivity.start(this)
    }

    override fun showLoader() {

    }

    override fun dismissLoader() {
    }
}
