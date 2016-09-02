package com.elpassion.secretmessenger.login

import rx.Subscription

class LoginController(val loginApi: LoginApi, val view: LoginView) {

    private var subscription: Subscription? = null

    fun onLogin(login: String, password: String) {
        if (login.isEmpty() || password.isEmpty()) {
            view.showLoginDataIncorrectError()
        } else {
            login(login, password)
        }
    }

    private fun login(login: String, password: String) {
        subscription = loginApi.login(login, password)
                .doOnSubscribe { view.showLoader() }
                .doOnUnsubscribe { view.dismissLoader() }
                .subscribe({
                    view.openHomeScreen()
                }, {
                    view.showLoginError()
                })
    }

    fun onDestroy() {
        subscription?.unsubscribe()
    }
}