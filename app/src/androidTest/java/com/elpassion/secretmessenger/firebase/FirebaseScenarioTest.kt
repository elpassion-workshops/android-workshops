package com.elpassion.secretmessenger.firebase

import com.elpassion.secretmessenger.login.FirebaseLoginApi
import com.elpassion.secretmessenger.register.FirebaseRegisterApi
import com.google.firebase.auth.FirebaseAuth
import org.junit.Test
import rx.Observable
import rx.observers.TestSubscriber
import java.util.*
import java.util.concurrent.TimeUnit

class FirebaseScenarioTest {

    val email = "TEST_" + UUID.randomUUID() + "@gmail.com"
    val password = "password"

    @Test
    fun shouldBeAbleToLoginAfterRegistration() {
        val subscriber = TestSubscriber<Unit>()
        Observable.just(Unit)
                .flatMap { FirebaseRegisterApi().register(email, password) }
                .map { FirebaseAuth.getInstance().signOut() }
                .flatMap { FirebaseLoginApi().login(email, password) }
                .subscribe(subscriber)

        subscriber.awaitTerminalEvent(10, TimeUnit.SECONDS)
        subscriber.assertValueCount(1)
    }
}