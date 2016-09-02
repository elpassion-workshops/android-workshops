package com.elpassion.secretmessenger.register.impl

import com.elpassion.secretmessenger.register.RegisterApi
import com.google.firebase.auth.FirebaseAuth
import com.kelvinapps.rxfirebase.RxFirebaseAuth
import rx.Observable

class FirebaseRegisterApi : RegisterApi {

    override fun register(login: String, password: String): Observable<Unit> {
        return RxFirebaseAuth
                .createUserWithEmailAndPassword(FirebaseAuth.getInstance(), login, password)
                .map { Unit }
    }
}