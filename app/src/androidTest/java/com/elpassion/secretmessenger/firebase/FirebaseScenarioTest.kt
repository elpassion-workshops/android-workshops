package com.elpassion.secretmessenger.firebase

import com.elpassion.secretmessenger.conversation.add.FirebaseConversationAddApi
import com.elpassion.secretmessenger.conversation.add.User
import com.elpassion.secretmessenger.conversation.details.FirebaseConversationDetailsApi
import com.elpassion.secretmessenger.conversation.details.Message
import com.elpassion.secretmessenger.conversation.list.FirebaseConversationListApi
import com.elpassion.secretmessenger.login.FirebaseLoginApi
import com.elpassion.secretmessenger.register.FirebaseRegisterApi
import com.google.firebase.auth.FirebaseAuth
import org.junit.Ignore
import org.junit.Test
import rx.Observable
import rx.observers.TestSubscriber
import java.util.*
import java.util.concurrent.TimeUnit

class FirebaseScenarioTest {

    val email = "TEST_" + UUID.randomUUID() + "@gmail.com"
    val password = "password"
    val message = "message"

    @Ignore
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

    @Ignore
    @Test
    fun shouldBeOnUserListAfterRegistration() {
        val subscriber = TestSubscriber<User>()
        Observable.just(Unit)
                .flatMap { FirebaseRegisterApi().register(email, password) }
                .flatMap { FirebaseConversationAddApi().fetchUsers() }
                .map { users -> users.first { it.email == email } }
                .subscribe(subscriber)

        subscriber.awaitTerminalEvent(10, TimeUnit.SECONDS)
        subscriber.assertValueCount(1)
    }

    @Ignore
    @Test
    fun shouldHasConversationOnListAfterSendingMessage() {
        val subscriber = TestSubscriber<Boolean>()
        Observable.just(Unit)
                .flatMap { FirebaseRegisterApi().register(email, password) }
                .flatMap { FirebaseConversationDetailsApi().sendMessageObservable(FirebaseAuth.getInstance().currentUser!!.uid, message) }
                .flatMap { FirebaseConversationListApi().getUserConversationList() }
                .map { it.isNotEmpty() }
                .subscribe(subscriber)

        subscriber.awaitTerminalEvent(10, TimeUnit.SECONDS)
        subscriber.assertValue(true)
    }

    @Ignore
    @Test
    fun shouldReceiveMessageAfterSendingMessage() {
        val subscriber = TestSubscriber<Message>()
        Observable.just(Unit)
                .flatMap { FirebaseRegisterApi().register(email, password) }
                .flatMap { FirebaseConversationDetailsApi().sendMessageObservable(FirebaseAuth.getInstance().currentUser!!.uid, message) }
                .flatMap { FirebaseConversationDetailsApi().getMessages(FirebaseAuth.getInstance().currentUser!!.uid) }
                .subscribe(subscriber)

        subscriber.awaitValueCount(1, 10, TimeUnit.SECONDS)
        subscriber.assertValueCount(1)
    }
}