package com.elpassion.secretmessenger.login

class LoginController(val loginApi: LoginApi, val view: LoginView) {
    fun onLogin(login: String, password: String) {
        if (login.isEmpty() ) {
            view.showLoginIncorrectError()
        } else {
            if (password.isNotEmpty()) {
                login()
            }
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