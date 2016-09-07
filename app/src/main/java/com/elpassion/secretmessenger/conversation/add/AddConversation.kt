package com.elpassion.secretmessenger.conversation.add

interface AddConversation {
    interface View {
        fun openConversationDetails(conversationUuid: String)
    }
}