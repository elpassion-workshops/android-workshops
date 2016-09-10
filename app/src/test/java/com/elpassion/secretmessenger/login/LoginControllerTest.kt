package com.elpassion.secretmessenger.login

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class LoginControllerTest {

    @Test
    fun shouldCallApiAfterOnLogin() {
        val api = mock<Login.Api>()
        val controller = LoginController(api)
        controller.onLogin("login")
        verify(api, times(1)).login(any())
    }

    @Test
    fun shouldCallApiWithCorrectLogin() {
        val api = mock<Login.Api>()
        val controller = LoginController(api)
        controller.onLogin(login = "login")
        verify(api, times(1)).login(login = "login")
    }

}

interface Login {
    interface Api {
        fun login(login: String)
    }
}

class LoginController(val api: Login.Api) {
    fun onLogin(login: String) {
        api.login(login)
    }
}
