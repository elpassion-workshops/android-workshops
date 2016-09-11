package com.elpassion.secretmessenger.register

class RegisterController(val api: Register.Api, val view: Register.View) {

    fun onCreate() {
        view.init()
    }

    fun onRegister(login: String, password: String, repeatedPassword: String) {
        if (login.isEmpty()) {
            view.showErrorEmptyLogin()
        } else if(password.isEmpty()) {
            view.showErrorEmptyPassword()
        } else if (password != repeatedPassword) {
            view.showErrorPasswordsDontMatch()
        } else if (login.isNotEmpty() && password.isNotEmpty()) {
            api.register(login, password).subscribe({

            }, {
                view.showErrorRegistrationFail()
            })
        }
    }

}