package com.elpassion.secretmessenger.firebase

import com.elpassion.secretmessenger.conversation.list.impl.FirebaseUsersApi
import com.elpassion.secretmessenger.login.impl.FirebaseLoginApi
import com.elpassion.secretmessenger.register.impl.FirebaseRegisterApi
import org.junit.Ignore
import org.junit.Test
import rx.Observable
import rx.observers.TestSubscriber
import java.util.*
import java.util.concurrent.TimeUnit

class FirebaseUsageScenario {

    private val email = "TEST_" + UUID.randomUUID() + "@test.test"
    private val password = "password"

    @Ignore("This test uses Internet")
    @Test
    fun shouldCheckFirebaseScenario() {
        val subscriber = TestSubscriber<String>()
        Observable.just(Unit)
                .flatMap { FirebaseRegisterApi().register(email, password) }
                .flatMap { FirebaseLoginApi().login(email, password) }
                .flatMap { FirebaseUsersApi().getUsers() }
                .map { users -> users.find { it == email }}
                .subscribe(subscriber)

        subscriber.awaitValueCount(1, 10, TimeUnit.SECONDS)
        subscriber.assertValue(email)
    }
}