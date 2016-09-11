package com.elpassion.secretmessenger.conversation.details

class ConversationDetailsController(val view: ConversationDetails.View, val api: ConversationDetails.Api) {
    fun onCreate() {
        api.getMessages().subscribe({
            view.showMessages(listOf(it))
        }, {
            view.showError()
        })
    }
}