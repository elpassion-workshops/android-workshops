package com.elpassion.secretmessenger.login

import com.nhaarman.mockito_kotlin.*
import org.junit.Test

class LoginControllerTest {

    @Test
    fun shouldCallApiWithCorrectLogin() {
        val api = mock<Login.Api>()
        val controller = LoginController(api)
        controller.onLogin(login = "login", password = "password")
        verify(api, times(1)).login(eq("login"), any())
    }

    @Test
    fun shouldCallApiWithCorrectLoginAndPassword() {
        val api = mock<Login.Api>()
        val controller = LoginController(api)
        controller.onLogin(login = "login", password = "password")
        verify(api, times(1)).login(login = "login", password = "password")
    }
}

interface Login {
    interface Api {
        fun login(login: String, password: String)
    }
}

class LoginController(val api: Login.Api) {
    fun onLogin(login: String, password: String) {
        api.login(login, password)
    }
}
