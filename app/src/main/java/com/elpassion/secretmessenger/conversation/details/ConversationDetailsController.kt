package com.elpassion.secretmessenger.conversation.details

import java.util.*

class ConversationDetailsController(val view: ConversationDetails.View,
                                    val api: ConversationDetails.Api,
                                    val friendId : String) {

    fun onCreate() {
        view.init()
        val oldMessages = ArrayList<Message>()
        api.getMessages(friendId)
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

    fun onMessageSend(messageToSend: String) {
        api.sendMessage(friendId, messageToSend)
    }
}