package com.elpassion.secretmessenger.conversation.list

import rx.Observable

interface ConversationList {

    interface Api {
        fun getUserConversationList() : Observable<List<Conversation>>
    }

    interface View {
        fun showError()
        fun showConversationList(conversationList: List<Conversation>)
    }

}