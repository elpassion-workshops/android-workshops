package com.elpassion.secretmessenger.register

import com.nhaarman.mockito_kotlin.*
import org.junit.Test

class RegisterControllerTest {

    @Test
    fun shouldCallApiWithCorrectLogin() {
        val api = mock<Register.Api>()
        val controller = RegisterController(api)
        controller.onRegister("login")
        verify(api, times(1)).register(login = "login")
    }

    @Test
    fun shouldNotCallApiWhenLoginIsEmpty() {
        val api = mock<Register.Api>()
        val controller = RegisterController(api)
        controller.onRegister("")
        verify(api, never()).register(any())
    }

}

class RegisterController(val api: Register.Api) {

    fun onRegister(login: String) {
        if (login.isEmpty()) {
            return
        }
       api.register(login)
    }
}

interface Register {
    interface Api {
        fun register(login: String)
    }

}
