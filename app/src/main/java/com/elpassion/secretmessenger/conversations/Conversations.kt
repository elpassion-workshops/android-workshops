package com.elpassion.secretmessenger.conversations

import rx.Observable

interface Conversations {
    interface Api {
        fun call(): Observable<List<com.elpassion.secretmessenger.conversations.Conversation>>

    }

    interface View {
        fun showConversationsPlaceholder()

        fun showConversations(listOf: List<com.elpassion.secretmessenger.conversations.Conversation>)

        fun showError()

        fun showLoader()

        fun hideLoader()

        fun openConversation(conversationUuid: String)
    }

}