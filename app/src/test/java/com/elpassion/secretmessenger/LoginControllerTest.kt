package com.elpassion.secretmessenger

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import rx.Observable
import rx.Observable.error

class LoginControllerTest {

    val loginApi = mock<LoginApi>()
    val view = mock<LoginView>()

    @Before
    fun setUp() {
        whenever(loginApi.login()).thenReturn(error(RuntimeException()))
    }

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

    @Test
    fun shouldShowErrorOnViewIfLoginFails() {
        login()
        verify(view, times(1)).showLoginError()
    }

    private fun login() {
        LoginController(loginApi, view).onLogin()
    }
}

interface LoginView {
    fun openHomeScreen()
    fun showLoginError()
}

interface LoginApi {
    fun login(): Observable<Unit>
}

class LoginController(val loginApi: LoginApi, val view: LoginView) {
    fun onLogin() {
        loginApi.login().subscribe({}, { view.showLoginError() })
        view.openHomeScreen()
    }
}