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
        whenever(loginApi.login(any(), any())).thenReturn(Observable.just(Unit))
    }

    @Test
    fun shouldCallApiWithProvidedCredentialsWhenOnLoginMethodIsCalled() {
        val login = "login"
        val password = "password"
        login(login, password)
        verify(loginApi, times(1)).login(login, password)
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
        verify(view, times(1)).showLoginDataIncorrectError()
    }

    @Test
    fun shouldNotCallApiIfLoginIsEmpty() {
        login(login = "")
        verify(loginApi, never()).login(any(), any())
    }

    @Test
    fun shouldNotCallLoginApiWhenPasswordIsEmpty() {
        login(password = "")
        verify(loginApi, never()).login(any(), any())
    }

    @Test
    fun shouldShowErrorIfUserTryToLoginWithEmptyPassword() {
        login(password = "")
        verify(view, times(1)).showLoginDataIncorrectError()
    }

    @Test
    fun shouldShowLoaderOnApiCall() {
        login()
        verify(view, times(1)).showLoader()
    }

    @Test
    fun shouldDismissLoaderWhenApiCallEnds() {
        login()
        verify(view, times(1)).dismissLoader()
    }

    @Test
    fun shouldNotDismissLoaderUntilApiCallEnds() {
        stubApiToReturnNever()
        login()
        verify(view, never()).dismissLoader()
    }

    @Test
    fun shouldDismissLoaderOnDestroyWhenApiCallIsStillInProgress() {
        stubApiToReturnNever()
        login()
        loginController.onDestroy()
        verify(view, times(1)).dismissLoader()
    }

    @Test
    fun shouldOpenRegisterScreenOnRegister() {
        loginController.onRegister()
        verify(view, times(1)).showRegisterScreen()
    }

    private fun login(login: String = "login", password: String = "password") = loginController.onLogin(login, password)

    private fun stubApiToReturnError() = whenever(loginApi.login(any(), any())).thenReturn(Observable.error(RuntimeException()))

    private fun stubApiToReturnNever() = whenever(loginApi.login(any(), any())).thenReturn(Observable.never())
}