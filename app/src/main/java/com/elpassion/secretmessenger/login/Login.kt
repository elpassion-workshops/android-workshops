package com.elpassion.secretmessenger.login

import com.elpassion.secretmessenger.common.Provider
import rx.Observable

interface Login {
    interface Api {
        fun login(login: String, password: String): Observable<Unit>
    }

    interface View {
        fun showConversationList()
        fun showError()
        fun showLoginPasswordError()
        fun init()
        fun loginInputChanges(): Observable<out String>
        fun passwordInputChanges(): Observable<out String>
        fun setStatus(status: String)
    }


    object ApiProvider : Provider<Api>({ FirebaseLoginApi() })
}