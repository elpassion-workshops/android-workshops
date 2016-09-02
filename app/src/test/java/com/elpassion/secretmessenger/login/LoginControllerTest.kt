package com.elpassion.secretmessenger.login

import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import rx.Observable

class LoginControllerTest {

    private val loginApi = mock<LoginApi>()
    private val view = mock<LoginView>()
    private val loginController = LoginController(loginApi, view)

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
        stubApiToReturnError()
        login()
        verify(view, times(1)).showLoginError()
    }

    @Test
    fun shouldNotOpenHomeScreenIfLoginFails() {
        stubApiToReturnError()
        login()
        verify(view, never()).openHomeScreen()
    }

    @Test
    fun shouldShowErrorIfUserTryToLoginWithEmptyLogin() {
        login(login = "")
        verify(view, times(1)).showLoginIncorrectError()
    }

    @Test
    fun shouldNotCallApiIfLoginIsEmpty() {
        login(login = "")
        verify(loginApi, never()).login()
    }

    @Test
    fun shouldNotCallLoginApiWhenPasswordIsEmpty() {
        login(password = "")
        verify(loginApi, never()).login()
    }

    private fun login(login: String = "login", password: String = "passowrd") = loginController.onLogin(login, password)

    private fun stubApiToReturnError() = whenever(loginApi.login()).thenReturn(Observable.error(RuntimeException()))

}