package com.elpassion.secretmessenger.conversation.add

import rx.Observable

interface AddConversation {
    interface View {
        fun openConversationDetails(conversationUuid: String)

        fun showError()
    }

    interface Api {
        fun addConversation(): Observable<String>
    }
}