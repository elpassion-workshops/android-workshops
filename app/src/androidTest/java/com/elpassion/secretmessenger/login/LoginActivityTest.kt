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
    val rule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun shouldDisplayLoginHeaderWithCorrectText() {
        onId(R.id.loginHeader).isDisplayed().hasText("Login")
    }

    @Test
    fun shouldHaveLoginInput() {
        onId(R.id.loginInput).isDisplayed()
    }
}

