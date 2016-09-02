package com.elpassion.secretmessenger

import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import rx.Observable

class LoginControllerTest {

    val loginApi = mock<LoginApi>()
    val view = mock<LoginView>()

    @Before
    fun setUp() {
        whenever(loginApi.login()).thenReturn(Observable.just(Unit))
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
        whenever(loginApi.login()).thenReturn(Observable.error(RuntimeException()))
        login()
        verify(view, times(1)).showLoginError()
    }

    @Test
    fun shouldNotOpenHomeScreenIfLoginFails() {
        whenever(loginApi.login()).thenReturn(Observable.error(RuntimeException()))
        login()
        verify(view, never()).openHomeScreen()
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
        loginApi.login().subscribe({
            view.openHomeScreen()
        }, {
            view.showLoginError()
        })
    }
}