package com.elpassion.secretmessenger.login

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.hasText
import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.onId
import com.elpassion.secretmessenger.R
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {

    @JvmField @Rule
    val rule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

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
}