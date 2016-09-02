package com.elpassion.secretmessenger.login

class LoginController(val loginApi: LoginApi, val view: LoginView) {
    fun onLogin(login: String, password: String) {
        if (login.isEmpty() || password.isEmpty()) {
            view.showLoginDataIncorrectError()
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