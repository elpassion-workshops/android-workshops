package com.elpassion.secretmessenger.conversation.add

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.kelvinapps.rxfirebase.DataSnapshotMapper
import com.kelvinapps.rxfirebase.RxFirebaseDatabase
import rx.Observable
import java.util.*

class FirebaseConversationAddApi : ConversationAdd.Api {
    override fun fetchUsers(): Observable<List<User>> {
        val query = FirebaseDatabase.getInstance()
                .reference
                .child("users")
        val mapper = DataSnapshotMapper.of(object : GenericTypeIndicator<HashMap<String, String>>() {})
        return RxFirebaseDatabase.observeSingleValueEvent(query, mapper)
                .map { users -> users.map { User(it.value) } }
    }
}