package com.elpassion.secretmessenger.login

import rx.Observable

interface Login {
    interface Api {
        fun login(login: String, password: String): Observable<Unit>
    }

    interface View {
        fun showConversationList()
        fun showError()

    }
}