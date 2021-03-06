package com.elpassion.secretmessenger.login

import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import rx.Observable

class LoginControllerTest {

    val view = mock<Login.View>()
    val api = mock<Login.Api>()
    val controller = LoginController(api, view)

    @Before
    fun setUp() {
        whenever(api.login(any(), any())).thenReturn(Observable.just(Unit))
    }

    @Test
    fun shouldCallApiWithCorrectLoginAndPassword() {
        login("login", "password")
        verify(api, times(1)).login(login = "login", password = "password")
    }

    @Test
    fun shouldOpenConversationListScreenWhenApiCallSucceed() {
        login()
        verify(view, times(1)).showConversationList()
    }

    @Test
    fun shouldShowErrorWhenApiCallFail() {
        stubApiToReturnError()
        login()
        verify(view, times(1)).showError()
    }

    @Test
    fun shouldNotShowErrorWhenApiCallSucceed() {
        login()
        verify(view, never()).showError()
    }

    @Test
    fun shouldNotCallApiWhenLoginIsEmpty() {
        login(login = "")
        verify(api, never()).login(any(), any())
    }

    @Test
    fun shouldNotCallApiWhenPasswordIsEmpty() {
        login(password = "")
        verify(api, never()).login(any(), any())
    }

    @Test
    fun shouldShowErrorThatLoginOrPasswordIsEmptyWhenLoginIsEmpty() {
        login(login = "")
        verify(view, times(1)).showLoginPasswordError()
    }

    @Test
    fun shouldShowErrorThatLoginOrPasswordIsEmptyWhenPasswordIsEmpty() {
        login(password =  "")
        verify(view, times(1)).showLoginPasswordError()
    }

    @Test
    fun shouldInitViewOnCreate() {
        controller.onCreate()
        verify(view).init()
    }

    private fun stubApiToReturnError() {
        whenever(api.login(any(), any())).thenReturn(Observable.error(RuntimeException()))
    }

    private fun login(login: String = "default", password: String = "password") {
        controller.onLogin(login = login, password = password)
    }
}