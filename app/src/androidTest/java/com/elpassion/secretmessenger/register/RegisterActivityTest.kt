package com.elpassion.secretmessenger.register

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.*
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.common.InitIntentsRule
import com.elpassion.secretmessenger.common.checkIntent
import com.elpassion.secretmessenger.conversation.list.ConversationListActivity
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

    @JvmField @Rule
    val intentsRule = InitIntentsRule()

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

    @Test
    fun shouldOpenConversationListAfterRegisterSucceeded() {
        stubApi()
        register()

        checkIntent(ConversationListActivity::class.java)
    }

    @Test
    fun shouldShowErrorMessageOnEmptyLogin() {
        register(login = "")

        onText(R.string.login_empty_error).isDisplayed()
    }

    private fun stubApi() {
        whenever(api.register(any(), any())).thenReturn(Observable.just(Unit))
    }

    private fun stubApiToReturnError() {
        whenever(api.register(any(), any())).thenReturn(Observable.error(RuntimeException()))
    }

    private fun register(login: String = "login") {
        onId(R.id.loginInput).typeText(login)
        onId(R.id.passwordInput).typeText("password")
        onId(R.id.repeatedPasswordInput).typeText("password")
        onId(R.id.registerButton).click()
    }

}

