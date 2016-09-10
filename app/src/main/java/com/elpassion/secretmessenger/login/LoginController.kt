package com.elpassion.secretmessenger.login

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
}