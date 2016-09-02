package com.elpassion.secretmessenger

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import rx.Observable

class RegisterControllerTest {

    val api = mock<RegisterApi>()
    val view = mock<RegisterView>()
    val controller = RegisterController(api, view)
    val login = "login"
    val  password = "password"

    @Before
    fun setUp() {
        stubApiForSuccess()
    }

    @Test
    fun shouldOpenHomeScreenAfterSuccessfulRegistration() {
        controller.register(login, password)
        verify(view).openHomeScreen()
    }

    @Test
    fun shouldShowErrorRegistrationErrorAfterUnsuccessfulRegistration() {
        stubApiForFailure()
        controller.register(login, password)
        verify(view).showRegisterError()
    }

    @Test
    fun shouldPassEmailAndPasswordToApi() {
        controller.register(login, password)
        verify(api).register(login, password)
    }

    private fun stubApiForSuccess() {
        whenever(api.register(login, password)).thenReturn(Observable.just(Unit))
    }

    private fun stubApiForFailure() {
        whenever(api.register(login, password)).thenReturn(Observable.error(RuntimeException()))
    }
}

interface RegisterApi {
    fun register(login: String, password: String): Observable<Unit>
}

interface RegisterView {
    fun openHomeScreen()
    fun showRegisterError()
}

class RegisterController(val api: RegisterApi, val view: RegisterView) {

    fun register(login: String, password: String) {
        api.register(login, password)
                .subscribe({
                    view.openHomeScreen()
                }, {
                    view.showRegisterError()
                })

    }
}