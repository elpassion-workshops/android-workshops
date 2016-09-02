package com.elpassion.secretmessenger

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class RegisterControllerTest {

    val view = mock<RegisterView>()
    val controller = RegisterController(view)

    @Test
    fun shouldOpenHomeScreenAfterSuccessfulRegistration() {
        controller.register()
        verify(view).openHomeScreen()
    }
}

interface RegisterView {
    fun openHomeScreen()
}

class RegisterController(val view: RegisterView) {

    fun register() {
        view.openHomeScreen()
    }
}