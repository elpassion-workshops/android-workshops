package com.elpassion.secretmessenger.login

import rx.Observable

interface LoginApi {
    fun login(): Observable<Unit>
}