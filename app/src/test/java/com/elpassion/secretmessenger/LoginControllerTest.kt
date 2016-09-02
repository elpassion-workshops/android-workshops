package com.elpassion.secretmessenger

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class LoginControllerTest {

    @Test
    fun shouldCallApiWhenOnLoginMethodIsCalled() {
        val loginApi = mock<LoginApi>()
        val view = mock<LoginView>()
        LoginController(loginApi, view).onLogin()
        verify(loginApi, times(1)).login()
    }

    @Test
    fun shouldOpenHomeScreenAfterLogin() {
        val loginApi = mock<LoginApi>()
        val view = mock<LoginView>()
        LoginController(loginApi, view).onLogin()
        verify(view, times(1)).openHomeScreen()
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