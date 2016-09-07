package com.elpassion.secretmessenger.conversation.add

class AddConversationController(val view: AddConversation.View) {

    fun onAddConversation() {
        view.openConversationDetails("")
    }
}