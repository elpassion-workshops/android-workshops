package com.elpassion.secretmessenger.login

import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.rule.ActivityTestRule
import android.support.v7.app.AppCompatActivity
import com.elpassion.android.commons.espresso.*
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.common.InitIntentsRule
import com.elpassion.secretmessenger.conversations.ConversationsActivity
import com.elpassion.secretmessenger.login.impl.LoginApiProvider
import com.elpassion.secretmessenger.register.impl.RegisterActivity
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
    @JvmField @Rule
    val intentRule = InitIntentsRule()

    @Test
    fun shouldDisplayLoginHeader() {
        onId(R.id.loginHeader).hasText(R.string.login_header)
    }

    @Test
    fun shouldDisplayLoginInputText() {
        onId(R.id.loginInput).isDisplayed()
    }

    @Test
    fun shouldDisplayPasswordHeader() {
        onId(R.id.passwordHeader).hasText(R.string.password_header)
    }

    @Test
    fun shouldDisplayPasswordInputText() {
        onId(R.id.passwordInput).isDisplayed()
    }

    @Test
    fun shouldDisplayLoginButton() {
        onId(R.id.loginButton).isDisplayed()
    }

    @Test
    fun shouldCallLoginApiAfterClickOnLoginButtonAndInputsAreNotEmpty() {
        login("asd", "asd")

        verify(loginApi).login(any(), any())
    }

    @Test
    fun shouldPassTextFromInputsToLoginApiAfterClickOnLoginButton() {
        val login = "asd"
        val password = "asd"
        login(login, password)

        verify(loginApi).login(login, password)
    }

    @Test
    fun shouldStartRegisterActivityAfterClickOnRegisterButton() {
        onId(R.id.registerButton).click()
        checkIntent(RegisterActivity::class.java)
    }

    @Test
    fun loginButtonShouldHaveCorrectName() {
        onId(R.id.loginButton).hasText(R.string.login_button)
    }

    @Test
    fun registerButtonShouldHaveCorrectName() {
        onId(R.id.registerButton).hasText(R.string.register_button)
    }

    @Test
    fun shouldOpenConversationsActivityWhenLoginSuccess() {
        login("asd", "asd")
        checkIntent(ConversationsActivity::class.java)
    }

    private fun checkIntent(clazz: Class<out AppCompatActivity>) {
        Intents.intended(IntentMatchers.hasComponent(clazz.name))
    }

    private fun login(login: String, password: String) {
        onId(R.id.loginInput).typeText(login)
        onId(R.id.passwordInput).typeText(password)

        onId(R.id.loginButton).click()
    }
}