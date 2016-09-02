package com.elpassion.secretmessenger.login

interface LoginView {
    fun openHomeScreen()
    fun showLoginError()
    fun showLoginDataIncorrectError()
    fun showLoader()
    fun dismissLoader()
}