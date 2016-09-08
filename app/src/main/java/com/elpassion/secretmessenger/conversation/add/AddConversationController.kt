package com.elpassion.secretmessenger.conversation.add

class AddConversationController(val view: AddConversation.View, val api: AddConversation.Api) {

    fun onCreate() {
        view.showUsers(emptyList())
    }

    fun onAddConversation() {
        api.addConversation()
                .subscribe({
                    view.openConversationDetails(it)
                }, {
                    view.showError()
                })
    }
}