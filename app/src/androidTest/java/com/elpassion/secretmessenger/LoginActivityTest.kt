package com.elpassion.secretmessenger

import android.support.test.rule.ActivityTestRule
import com.elpassion.secretmessenger.login.LoginActivity
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {

    @JvmField @Rule
    val rule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    @Test
    fun startLoginActivity() {
    }
}