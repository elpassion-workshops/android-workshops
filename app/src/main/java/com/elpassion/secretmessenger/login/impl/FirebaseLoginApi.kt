package com.elpassion.secretmessenger.login.impl

import com.elpassion.secretmessenger.login.Login
import com.google.firebase.auth.FirebaseAuth
import com.kelvinapps.rxfirebase.RxFirebaseAuth
import rx.Observable

class FirebaseLoginApi : Login.Api {

    override fun login(login: String, password: String): Observable<Unit> {
        return RxFirebaseAuth
                .signInWithEmailAndPassword(FirebaseAuth.getInstance(), "email@wp.pl", "password")
                .map { Unit }
    }
}