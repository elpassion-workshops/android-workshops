package com.elpassion.secretmessenger.register

class RegisterController(val api: RegisterApi, val view: RegisterView) {

    fun register(login: String, password: String) {
        api.register(login, password)
                .subscribe({
                    view.openHomeScreen()
                }, {
                    view.showRegisterError()
                })

    }
}
