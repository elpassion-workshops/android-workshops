package com.elpassion.secretmessenger.conversation.list

import rx.Observable

interface Conversations {
    interface Api {
        fun call(): Observable<List<Conversation>>

    }

    interface View {
        fun showConversationsPlaceholder()

        fun showConversations(listOf: List<Conversation>)

        fun showError()

        fun showLoader()

        fun hideLoader()

        fun openConversationScreen(conversationUuid: String)

        fun openAddConversationScreen()
    }

}