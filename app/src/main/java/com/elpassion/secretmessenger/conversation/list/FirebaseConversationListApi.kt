package com.elpassion.secretmessenger.conversation.list

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.kelvinapps.rxfirebase.DataSnapshotMapper
import com.kelvinapps.rxfirebase.RxFirebaseDatabase
import rx.Observable
import java.util.*

class FirebaseConversationListApi : ConversationList.Api {
    override fun getUserConversationList(): Observable<List<Conversation>> {
        val query = FirebaseDatabase.getInstance()
                .reference
                .child("conversations")
        val mapper = DataSnapshotMapper.of(object : GenericTypeIndicator<HashMap<String, HashMap<String, HashMap<String, String>>>>() {})
        return RxFirebaseDatabase.observeSingleValueEvent(query, mapper)
                .map { getUserConversations(it) }
    }

    private fun getUserConversations(conversations: HashMap<String, HashMap<String, HashMap<String, String>>>): List<Conversation> {
        return conversations[uid()]!!.map { Conversation(it.key) }
    }

    private fun uid() = FirebaseAuth.getInstance().currentUser!!.uid
}