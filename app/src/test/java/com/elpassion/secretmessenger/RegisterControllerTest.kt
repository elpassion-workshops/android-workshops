package com.elpassion.secretmessenger

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import rx.Observable

class RegisterControllerTest {

    val api = mock<RegisterApi>()
    val view = mock<RegisterView>()
    val controller = RegisterController(api, view)

    @Test
    fun shouldOpenHomeScreenAfterSuccessfulRegistration() {
        stubApiForSuccess()
        controller.register()
        verify(view).openHomeScreen()
    }

    @Test
    fun shouldShowErrorRegistrationErrorAfterUnsuccessfulRegistration() {
        stubApiForFailure()
        controller.register()
        verify(view).showRegisterError()
    }

    private fun stubApiForSuccess() {
        whenever(api.register()).thenReturn(Observable.just(Unit))
    }

    private fun stubApiForFailure() {
        whenever(api.register()).thenReturn(Observable.error(RuntimeException()))
    }
}

interface RegisterApi {
    fun register(): Observable<Unit>
}

interface RegisterView {
    fun openHomeScreen()
    fun showRegisterError()
}

class RegisterController(val api: RegisterApi, val view: RegisterView) {

    fun register() {
        api.register()
                .subscribe({
                    view.openHomeScreen()
                }, {
                    view.showRegisterError()
                })

    }
}