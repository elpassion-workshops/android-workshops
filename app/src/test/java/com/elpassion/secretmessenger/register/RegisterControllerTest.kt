package com.elpassion.secretmessenger.register

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class RegisterControllerTest {

    @Test
    fun shouldCallApiOnRegister() {
        val api = mock<Register.Api>()
        val controller = RegisterController(api)
        controller.register()
        verify(api, times(1)).register()
    }
}

class RegisterController(val api: Register.Api) {
    fun register() {
       api.register()
    }
}

interface Register {
    interface Api {
        fun register()
    }

}
