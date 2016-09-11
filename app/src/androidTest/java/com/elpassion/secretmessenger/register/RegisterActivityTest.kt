package com.elpassion.secretmessenger.register

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.*
import com.elpassion.secretmessenger.R
import com.nhaarman.mockito_kotlin.*
import org.junit.Rule
import org.junit.Test
import rx.Observable

class RegisterActivityTest {
    val api = mock<Register.Api>()

    @JvmField @Rule
    val rule = object : ActivityTestRule<RegisterActivity>(RegisterActivity::class.java) {
        override fun beforeActivityLaunched() {

            Register.ApiProvider.override = { api }
        }
    }

    @Test
    fun shouldDisplayRegisterHeaderWithCorrectText() {
        onId(R.id.registerHeader).isDisplayed().hasText("Register")
    }

    @Test
    fun shouldHaveLoginInput() {
        onId(R.id.loginInput).isDisplayed()
    }

    @Test
    fun shouldHavePasswordInput() {
        onId(R.id.passwordInput).isDisplayed()
    }

    @Test
    fun shouldHaveRepeatedPasswordInput() {
        onId(R.id.repeatedPasswordInput).isDisplayed()
    }

    @Test
    fun shouldCallApiIfLoginAndPasswordAreCorrectAfterClickOnRegisterButton() {
        stubApi()
        register()

        verify(api, times(1)).register(any(), any())
    }

    private fun stubApi() {
        whenever(api.register(any(), any())).thenReturn(Observable.just(Unit))
    }

    private fun register() {
        onId(R.id.loginInput).typeText("login")
        onId(R.id.passwordInput).typeText("password")
        onId(R.id.repeatedPasswordInput).typeText("password")
        onId(R.id.registerButton).click()
    }

}

