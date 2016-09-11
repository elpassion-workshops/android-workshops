package com.elpassion.secretmessenger.register

import com.nhaarman.mockito_kotlin.*
import org.junit.Test

class RegisterControllerTest {

    val api = mock<Register.Api>()
    val controller = RegisterController(api)

    @Test
    fun shouldCallApiWithCorrectLogin() {
        register()
        verify(api, times(1)).register(eq("login"), any())
    }

    @Test
    fun shouldNotCallApiWhenLoginIsEmpty() {
        register("", "password")
        verify(api, never()).register(any(), any())
    }

    @Test
    fun shouldCallApiWithCorrectPassword() {
        register()
        verify(api, times(1)).register(any(), eq("password"))
    }

    @Test
    fun shouldNotCallApiWhenPasswordIsEmpty() {
        register(password = "")
        verify(api, never()).register(any(), any())
    }

    @Test
    fun shouldCallApiWhenPasswordsAreEqual() {
        register()
        verify(api, times(1)).register(any(), eq("password"))
    }

    private fun register(login: String = "login", password: String = "password", repeatedPassword: String = password) {
        controller.onRegister(login, password, repeatedPassword)
    }

}

class RegisterController(val api: Register.Api) {

    fun onRegister(login: String, password: String, repeatedPassword: String) {
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
