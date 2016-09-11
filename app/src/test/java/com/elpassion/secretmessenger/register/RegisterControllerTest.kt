package com.elpassion.secretmessenger.register

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class RegisterControllerTest {

    @Test
    fun shouldCallApiWithCorrectLogin() {
        val api = mock<Register.Api>()
        val controller = RegisterController(api)
        controller.onRegister("login")
        verify(api, times(1)).register(login = "login")
    }

}

class RegisterController(val api: Register.Api) {

    fun onRegister(login: String) {
       api.register(login)
    }
}

interface Register {
    interface Api {
        fun register(login: String)
    }

}
