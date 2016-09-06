package com.elpassion.secretmessenger.login

import rx.Observable

interface Login {

    interface Api {
        fun login(login: String, password: String): Observable<Unit>
    }

    interface View {
        fun openHomeScreen()
        fun showLoginError()
        fun showLoginDataIncorrectError()
        fun showLoader()
        fun dismissLoader()
        fun showRegisterScreen()
    }

}
