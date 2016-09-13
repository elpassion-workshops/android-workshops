package com.elpassion.secretmessenger.login

import rx.Observable

class LoginController(val api: Login.Api, val view: Login.View) {
    fun onLogin(login: String, password: String) {
        if (login.isNotEmpty() && password.isNotEmpty()) {
            api.login(login, password)
                    .subscribe({
                        view.showConversationList()
                    }, {
                        view.showError()
                    })
        } else {
            view.showLoginPasswordError()
        }
    }

    fun onCreate() {
        view.loginClicks().subscribe { onLogin(view.login.trim(), view.password) }
        Observable.combineLatest(view.loginInputChanges(), view.passwordInputChanges()) {
            login: String, password: String ->
            "Log in $login! (pass len: ${password.length})"
        }.subscribe { view.setStatus(it) }
    }
}