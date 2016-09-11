package com.elpassion.secretmessenger.conversation.details

import java.util.*

class ConversationDetailsController(val view: ConversationDetails.View, val api: ConversationDetails.Api) {
    fun onCreate() {
        val oldMessages = ArrayList<Message>()
        api.getMessages()
                .map {
                    oldMessages.add(it)
                    ArrayList(oldMessages)
                }
                .subscribe({
            view.showMessages(it)
        }, {
            view.showError()
        })
    }
}