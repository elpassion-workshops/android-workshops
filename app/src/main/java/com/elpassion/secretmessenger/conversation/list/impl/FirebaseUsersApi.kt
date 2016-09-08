package com.elpassion.secretmessenger.conversation.list.impl

import com.elpassion.secretmessenger.conversation.add.AddConversation
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.kelvinapps.rxfirebase.DataSnapshotMapper
import com.kelvinapps.rxfirebase.RxFirebaseDatabase
import rx.Observable
import java.util.*

class FirebaseUsersApi : AddConversation.UsersApi {
    override fun getUsers(): Observable<List<String>> {
        val reference = FirebaseDatabase
                .getInstance()
                .reference
                .child("users")
        return RxFirebaseDatabase.observeSingleValueEvent(reference, DataSnapshotMapper.of(object : GenericTypeIndicator<HashMap<String, String>>() {

        })).map { it.values.toList() }
    }

}