package com.elpassion.secretmessenger

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class LoginControllerTest {

    val loginApi = mock<LoginApi>()
    val view = mock<LoginView>()

    @Test
    fun shouldCallApiWhenOnLoginMethodIsCalled() {
        login()
        verify(loginApi, times(1)).login()
    }

    @Test
    fun shouldOpenHomeScreenAfterLogin() {
        login()
        verify(view, times(1)).openHomeScreen()
    }

    private fun login() {
        LoginController(loginApi, view).onLogin()
    }
}

interface LoginView {
    fun openHomeScreen()
}

interface LoginApi {
    fun login()
}

class LoginController(val loginApi: LoginApi, val view: LoginView) {
    fun onLogin() {
        loginApi.login()
        view.openHomeScreen()
    }
}