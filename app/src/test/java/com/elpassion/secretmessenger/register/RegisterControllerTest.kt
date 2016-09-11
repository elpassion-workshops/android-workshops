package com.elpassion.secretmessenger.register

import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import rx.Observable

class RegisterControllerTest {

    val api = mock<Register.Api>()
    val view = mock<Register.View>()
    val controller = RegisterController(api, view)

    @Before
    fun setUp() {
        whenever(api.register(any(), any())).thenReturn(Observable.just(Unit))
    }

    @Test
    fun shouldCallApiWithCorrectLogin() {
        register()
        verify(api, times(1)).register(eq("login"), any())
    }

    @Test
    fun shouldShowErrorWhenEmptyLogin() {
        register(login = "")
        verify(view, times(1)).showErrorEmptyLogin()
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

    @Test
    fun shouldNotShowErrorWhenCallSucceed() {
        register()
        verify(view, never()).showErrorRegistrationFail()
    }

    @Test
    fun shouldShowErrorWhenCallReturnsError() {
        stubApiToReturnError()
        register()
        verify(view).showErrorRegistrationFail()
    }

    @Test
    fun shouldShowLoaderWhenRegistrationStarted() {
        register()
        verify(view).showLoader()
    }

    @Test
    fun shouldDismissLoaderWhenRegistrationFinished() {
        register()
        verify(view).dismissLoader()
    }

    private fun stubApiToReturnError() {
        whenever(api.register(any(), any())).thenReturn(Observable.error(RuntimeException()))
    }

    private fun register(login: String = "login", password: String = "password", repeatedPassword: String = password) {
        controller.onRegister(login, password, repeatedPassword)
    }

}

