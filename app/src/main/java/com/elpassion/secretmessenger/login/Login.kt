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
        fun loginInputChanges(): Observable<out String>
        fun passwordInputChanges(): Observable<out String>
        fun loginClicks(): Observable<Unit>
        fun setStatus(status: String)
        val login: String
        val password: String
    }


    object ApiProvider : Provider<Api>({ FirebaseLoginApi() })
}