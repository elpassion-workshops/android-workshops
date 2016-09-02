package com.elpassion.secretmessenger.login

class LoginController(val loginApi: LoginApi, val view: LoginView) {
    fun onLogin(login: String, password: String) {
        if (login.isEmpty() || password.isEmpty()) {
            view.showLoginDataIncorrectError()
        } else {
            login(login, password)
        }
    }

    private fun login(login: String, password: String) {
        view.showLoader()
        view.dismissLoader()
        loginApi.login(login, password).subscribe({
            view.openHomeScreen()
        }, {
            view.showLoginError()
        })
    }
}