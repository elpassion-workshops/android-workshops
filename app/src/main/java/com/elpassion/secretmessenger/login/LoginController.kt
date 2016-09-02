package com.elpassion.secretmessenger.login

class LoginController(val loginApi: LoginApi, val view: LoginView) {
    fun onLogin(login: String) {
        if (login.isEmpty()) {
            view.showLoginIncorrectError()
        } else {
            login()
        }
    }

    private fun login() {
        loginApi.login().subscribe({
            view.openHomeScreen()
        }, {
            view.showLoginError()
        })
    }
}