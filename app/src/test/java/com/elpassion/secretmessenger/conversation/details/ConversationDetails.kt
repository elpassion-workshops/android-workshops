package com.elpassion.secretmessenger.conversation.details

import rx.Observable

interface ConversationDetails {
    interface View {
        fun showMessages(messages: List<Message>)
        fun showError()
    }

    interface Api {
        fun getMessages(): Observable<Message>
    }
}