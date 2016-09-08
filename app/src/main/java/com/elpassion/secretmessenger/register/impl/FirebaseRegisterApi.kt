package com.elpassion.secretmessenger.register.impl

import com.elpassion.secretmessenger.register.RegisterApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.kelvinapps.rxfirebase.RxFirebaseAuth
import rx.AsyncEmitter
import rx.Observable

class FirebaseRegisterApi : RegisterApi {

    override fun register(login: String, password: String): Observable<Unit> {
        return RxFirebaseAuth
                .createUserWithEmailAndPassword(FirebaseAuth.getInstance(), login, password)
                .flatMap { putUser(login) }
    }

    fun putUser(login: String): Observable<Unit> {
        return Observable.fromAsync<Unit>({ asyncEmitter ->
            try {
                FirebaseDatabase
                        .getInstance()
                        .reference
                        .child("users")
                        .child(FirebaseAuth
                                .getInstance()
                                .currentUser!!
                                .uid)
                        .setValue(login)
                        .addOnSuccessListener {
                            asyncEmitter.onNext(Unit)
                            asyncEmitter.onCompleted()
                        }
                        .addOnFailureListener {
                            asyncEmitter.onError(it)
                        }
            } catch(e: Exception) {
                asyncEmitter.onError(e)
            }
        }, AsyncEmitter.BackpressureMode.BUFFER)
    }
}