package com.elpassion.secretmessenger.login

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class LoginControllerTest {

    @Test
    fun shouldCallApiAfterOnLogin() {
        val api = mock<Login.Api>()
        val controller = LoginController(api)
        controller.onLogin()
        verify(api).login()
    }

}

interface Login {
    interface Api {
        fun login()
    }
}

class LoginController(val api: Login.Api) {
    fun onLogin() {
        api.login()
    }
}
