package com.elpassion.secretmessenger.register

import com.nhaarman.mockito_kotlin.*
import org.junit.Test

class RegisterControllerTest {

    val api = mock<Register.Api>()
    val view = mock<Register.View>()
    val controller = RegisterController(api, view)

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
        register(password = "password", repeatedPassword = "password")
        verify(api, times(1)).register(any(), eq("password"))
    }

    @Test
    fun shouldNotCallApiWhenPasswordsDiffer() {
        register(password = "password", repeatedPassword = "different")
        verify(api, never()).register(any(), any())
    }

    @Test
    fun shouldShowErrorWhenPasswordsDiffer() {
        register(password = "password", repeatedPassword = "different")
        verify(view).showErrorPasswordsDontMatch()
    }

    @Test
    fun shouldShowErrorWhenEmptyPassword() {
        register(password = "")
        verify(view).showErrorEmptyPassword()
    }

    private fun register(login: String = "login", password: String = "password", repeatedPassword: String = password) {
        controller.onRegister(login, password, repeatedPassword)
    }

}

class RegisterController(val api: Register.Api, val view: Register.View) {

    fun onRegister(login: String, password: String, repeatedPassword: String) {
        if (password.isEmpty()) {
            view.showErrorEmptyPassword()
        } else if (password != repeatedPassword) {
            view.showErrorPasswordsDontMatch()
        } else if (login.isNotEmpty() && password.isNotEmpty()) {
            api.register(login, password)
        }
    }

}

interface Register {
    interface Api {
        fun register(login: String, password: String)
    }

    interface View {
        fun showErrorPasswordsDontMatch()
        fun showErrorEmptyPassword()
    }

}
