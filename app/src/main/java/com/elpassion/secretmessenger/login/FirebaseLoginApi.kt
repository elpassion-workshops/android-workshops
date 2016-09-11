package com.elpassion.secretmessenger.login

import com.google.firebase.auth.FirebaseAuth
import com.kelvinapps.rxfirebase.RxFirebaseAuth
import rx.Observable

class FirebaseLoginApi : Login.Api {
    override fun login(login: String, password: String): Observable<Unit> {
        return RxFirebaseAuth.signInWithEmailAndPassword(FirebaseAuth.getInstance(), login, password).map { Unit }
    }
}