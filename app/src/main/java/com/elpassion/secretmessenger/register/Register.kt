package com.elpassion.secretmessenger.register

import com.elpassion.secretmessenger.common.Provider
import rx.Observable

interface Register {
    interface Api {
        fun register(login: String, password: String): Observable<Unit>
    }

    interface View {
        fun showErrorPasswordsDontMatch()
        fun showErrorEmptyPassword()
        fun showErrorEmptyLogin()
        fun showErrorRegistrationFail()
        fun init()
        fun showConversationList()
        fun showLoader()
        fun dismissLoader()
    }
    object ApiProvider : Provider<Register.Api>({ throw NotImplementedError() })
}