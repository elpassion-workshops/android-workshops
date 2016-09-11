package com.elpassion.secretmessenger.register

import com.nhaarman.mockito_kotlin.*
import org.junit.Test

class RegisterControllerTest {

    val api = mock<Register.Api>()
    val controller = RegisterController(api)

    @Test
    fun shouldCallApiWithCorrectLogin() {
        controller.onRegister(login = "login", password = "password")
        verify(api, times(1)).register(eq("login"), any())
    }

    @Test
    fun shouldNotCallApiWhenLoginIsEmpty() {
        controller.onRegister("", "password")
        verify(api, never()).register(any(), any())
    }

    @Test
    fun shouldCallApiWithCorrectPassword() {
        controller.onRegister(login = "login", password = "password")
        verify(api, times(1)).register(any(), eq("password"))
    }

    @Test
    fun shouldNotCallApiWhenPasswordIsEmpty() {
        controller.onRegister("login", "")
        verify(api, never()).register(any(), any())
    }


}

class RegisterController(val api: Register.Api) {

    fun onRegister(login: String, password: String) {
        if (login.isNotEmpty() && password.isNotEmpty()) {
            api.register(login, password)
        }
    }
}

interface Register {
    interface Api {
        fun register(login: String, password: String)
    }

}
