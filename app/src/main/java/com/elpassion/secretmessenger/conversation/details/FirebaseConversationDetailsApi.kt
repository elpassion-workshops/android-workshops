package com.elpassion.secretmessenger.conversation.details

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import rx.AsyncEmitter
import rx.Observable

class FirebaseConversationDetailsApi : ConversationDetails.Api {
    override fun getMessages(): Observable<Message> {
        return Observable.never()
    }

    override fun sendMessage(friendId:String, messageToSend: String) {
        sendMessageObservable(friendId, messageToSend).subscribe({
            Log.e("FirebaseSendMessage", "Message sent")
        },{
            Log.e("FirebaseSendMessage", "Message not sent")
        })
    }

    fun sendMessageObservable(friendId:String, messageToSend: String): Observable<Unit> {
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
                .child(uid)
                .child(uid)
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