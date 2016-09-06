package com.elpassion.secretmessenger.login

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.*
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.login.impl.LoginApiProvider
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Rule
import org.junit.Test
import rx.Observable

class LoginActivityTest {

    val loginApi = mock<LoginApi>() {
        on { login(any(), any()) } doReturn Observable.just(Unit)
    }

    @JvmField @Rule
    val rule = object : ActivityTestRule<LoginActivity>(LoginActivity::class.java) {
        override fun beforeActivityLaunched() {
            LoginApiProvider.override = { loginApi }
        }
    }

    @Test
    fun shouldDisplayLoginHeader() {
        onId(R.id.login_header).hasText(R.string.login_header)
    }

    @Test
    fun shouldDisplayLoginInputText() {
        onId(R.id.login_input).isDisplayed()
    }

    @Test
    fun shouldDisplayPasswordHeader() {
        onId(R.id.password_header).hasText(R.string.password_header)
    }

    @Test
    fun shouldDisplayPasswordInputText() {
        onId(R.id.password_input).isDisplayed()
    }

    @Test
    fun shouldDisplayLoginButton() {
        onId(R.id.login_button).isDisplayed()
    }

    @Test
    fun shouldCallLoginApiAfterClickOnLoginButtonAndInputsAreNotEmpty() {
        onId(R.id.login_input).typeText("asd")
        onId(R.id.password_input).typeText("asd")

        onId(R.id.login_button).click()

        verify(loginApi).login(any(), any())
    }
}