package com.elpassion.secretmessenger.register

import rx.Observable

interface RegisterApi {
    fun register(login: String, password: String): Observable<Unit>
}
