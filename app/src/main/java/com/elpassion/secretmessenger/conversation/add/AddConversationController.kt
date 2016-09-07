package com.elpassion.secretmessenger.conversation.add

class AddConversationController(val view: AddConversation.View, val api: AddConversation.Api) {

    fun onAddConversation() {
        view.openConversationDetails("")
        api.addConversation()
                .subscribe({}, { view.showError() })
    }
}