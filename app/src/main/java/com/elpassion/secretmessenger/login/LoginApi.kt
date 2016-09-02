package com.elpassion.secretmessenger.login

import rx.Observable

interface LoginApi {
    fun login(login: String, password: String): Observable<Unit>
}