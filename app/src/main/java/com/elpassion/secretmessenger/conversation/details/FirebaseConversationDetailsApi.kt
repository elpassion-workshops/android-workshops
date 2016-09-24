package com.elpassion.secretmessenger.conversation.details

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.kelvinapps.rxfirebase.DataSnapshotMapper
import com.kelvinapps.rxfirebase.RxFirebaseChildEvent
import com.kelvinapps.rxfirebase.RxFirebaseDatabase
import rx.AsyncEmitter
import rx.Observable

class FirebaseConversationDetailsApi : ConversationDetails.Api {
    override fun getMessages(friendId: String): Observable<Message> {
        val uid = FirebaseAuth
                .getInstance()
                .currentUser!!
                .uid
        val query = FirebaseDatabase
                .getInstance()
                .reference
                .child("conversations")
                .child(if (uid < friendId) uid else friendId)
                .child(if (uid < friendId) friendId else uid)
        val mapper = rx.functions.Func1<RxFirebaseChildEvent<DataSnapshot>, RxFirebaseChildEvent<String>> {
            RxFirebaseChildEvent(DataSnapshotMapper.of(String::class.java).call(it.value), it.eventType)
        }
        return RxFirebaseDatabase.observeChildEvent(query, mapper)
                .filter { it.eventType == RxFirebaseChildEvent.EventType.ADDED }
                .map { Message(it.value) }
    }

    override fun sendMessage(friendId: String, messageToSend: String) {
        sendMessageObservable(friendId, messageToSend).subscribe({
            Log.e("FirebaseSendMessage", "Message sent")
        }, {
            Log.e("FirebaseSendMessage", "Message not sent")
        })
    }

    fun sendMessageObservable(friendId: String, messageToSend: String): Observable<Unit> {
        return Observable.fromAsync<Unit>({ asyncEmitter ->
            try {
                val uid = FirebaseAuth
                        .getInstance()
                        .currentUser!!
                        .uid
                FirebaseDatabase
                        .getInstance()
                        .reference
                        .child("conversations")
                        .child(if (uid < friendId) uid else friendId)
                        .child(if (uid < friendId) friendId else uid)
                        .child(System.currentTimeMillis().toString())
                        .setValue(messageToSend)
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