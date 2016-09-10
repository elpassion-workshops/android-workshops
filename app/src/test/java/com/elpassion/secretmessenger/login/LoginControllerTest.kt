package com.elpassion.secretmessenger.login

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class LoginControllerTest {

    val view = mock<Login.View>()
    val api = mock<Login.Api>()
    val controller = LoginController(api, view)

    @Test
    fun shouldCallApiWithCorrectLoginAndPassword() {
        login("login", "password")
        verify(api, times(1)).login(login = "login", password = "password")
    }

    @Test
    fun shouldOpenConversationListScreenWhenApiCallSucceed() {
        login()
        verify(view, times(1)).showConversationList()
    }

    @Test
    fun shouldShowErrorWhenApiCallFail() {
        login()
        verify(view, times(1)).showError()
    }

    private fun login(login: String = "", password: String = "") {
        controller.onLogin(login = login, password = password)
    }
}

interface Login {
    interface Api {
        fun login(login: String, password: String)
    }

    interface View {
        fun showConversationList()
        fun showError()

    }
}

class LoginController(val api: Login.Api, val view: Login.View) {
    fun onLogin(login: String, password: String) {
        api.login(login, password)
        view.showConversationList()
        view.showError()
    }
}
