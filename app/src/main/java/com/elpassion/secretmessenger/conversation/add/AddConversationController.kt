package com.elpassion.secretmessenger.conversation.add

class AddConversationController(val view: AddConversation.View, val api: AddConversation.Api) {

    fun onAddConversation() {
        api.addConversation()
                .subscribe({
                    view.openConversationDetails("")
                }, {
                    view.showError()
                })
    }
}