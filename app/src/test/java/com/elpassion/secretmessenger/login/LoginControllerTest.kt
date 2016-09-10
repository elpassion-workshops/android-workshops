package com.elpassion.secretmessenger.login

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class LoginControllerTest {

    @Test
    fun shouldCallApiWithCorrectLoginAndPassword() {
        val api = mock<Login.Api>()
        val controller = LoginController(api, mock())
        controller.onLogin(login = "login", password = "password")
        verify(api, times(1)).login(login = "login", password = "password")
    }

    @Test
    fun shouldOpenConversationListScreenWhenApiCallSucceed() {
        val view = mock<Login.View>()
        val api = mock<Login.Api>()
        val controller = LoginController(api, view)
        controller.onLogin("", "")
        verify(view, times(1)).showConversationList()
    }
}

interface Login {
    interface Api {
        fun login(login: String, password: String)
    }

    interface View {
        fun showConversationList()

    }
}

class LoginController(val api: Login.Api, val view: Login.View) {
    fun onLogin(login: String, password: String) {
        api.login(login, password)
        view.showConversationList()
    }
}
