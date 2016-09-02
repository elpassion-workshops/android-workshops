package com.elpassion.secretmessenger

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class LoginControllerTest {

    @Test
    fun shouldCallApiWhenOnLoginMethodIsCalled() {
        val loginApi = mock<LoginApi>()
        LoginController(loginApi).onLogin()
        verify(loginApi, times(1)).login()
    }

}

interface LoginApi {
    fun login()
}

class LoginController(val loginApi: LoginApi) {
    fun onLogin() {
        loginApi.login()
    }
}