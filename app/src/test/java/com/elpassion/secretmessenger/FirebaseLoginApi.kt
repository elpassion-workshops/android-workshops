package com.elpassion.secretmessenger

import com.google.firebase.auth.FirebaseAuth
import com.kelvinapps.rxfirebase.RxFirebaseAuth
import rx.Observable

class FirebaseLoginApi : LoginApi {

    override fun login(): Observable<Unit> {
        return RxFirebaseAuth
                .signInWithEmailAndPassword(FirebaseAuth.getInstance(), "email@wp.pl", "password")
                .map { Unit }
    }
}