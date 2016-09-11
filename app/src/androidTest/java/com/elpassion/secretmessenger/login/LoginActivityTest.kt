package com.elpassion.secretmessenger.login

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

class LoginActivityTest {

    val api = mock<Login.Api>()

    @JvmField @Rule
    val rule = object : ActivityTestRule<LoginActivity>(LoginActivity::class.java) {
        override fun beforeActivityLaunched() {
            Login.ApiProvider.override = { api }
        }
    }

    @JvmField @Rule
    val intentsRule = InitIntentsRule()

    @Test
    fun shouldDisplayLoginHeaderWithCorrectText() {
        onId(R.id.loginHeader).isDisplayed().hasText("Login")
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
    fun shouldCallApiIfLoginAndPasswordAreNotEmptyAfterClickOnLoginButton() {
        stubApi()
        login()

        verify(api, times(1)).login(any(), any())
    }

    @Test
    fun shouldOpenConversationListAfterLoginSucceed() {
        stubApi()
        login()

        checkIntent(ConversationListActivity::class.java)
    }

    private fun login() {
        onId(R.id.loginInput).typeText("login")
        onId(R.id.passwordInput).typeText("password")
        onId(R.id.loginButton).click()
    }

    private fun stubApi() {
        whenever(api.login(any(), any())).thenReturn(Observable.just(Unit))
    }
}

