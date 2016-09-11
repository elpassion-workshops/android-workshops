package com.elpassion.secretmessenger.conversation.details

import com.elpassion.secretmessenger.common.Provider
import rx.Observable

interface ConversationDetails {
    interface View {
        fun showMessages(messages: List<Message>)
        fun showError()
    }

    interface Api {
        fun getMessages(): Observable<Message>
        fun sendMessage(messageToSend: String)
    }

    object ApiProvider : Provider<Api>({ throw NotImplementedError() })
}