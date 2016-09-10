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

    private fun stubApiToReturnError() {
        whenever(api.login(any(), any())).thenReturn(Observable.error(RuntimeException()))
    }

    private fun login(login: String = "", password: String = "") {
        controller.onLogin(login = login, password = password)
    }
}

interface Login {
    interface Api {
        fun login(login: String, password: String): Observable<Unit>
    }

    interface View {
        fun showConversationList()
        fun showError()

    }
}

class LoginController(val api: Login.Api, val view: Login.View) {
    fun onLogin(login: String, password: String) {
        api.login(login, password)
                .subscribe({
                    view.showConversationList()
                }, {
                    view.showError()
                })
    }
}
