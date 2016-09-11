package com.elpassion.secretmessenger.register

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.hasText
import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.onId
import com.elpassion.secretmessenger.R
import com.nhaarman.mockito_kotlin.mock
import org.junit.Rule
import org.junit.Test

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

}

