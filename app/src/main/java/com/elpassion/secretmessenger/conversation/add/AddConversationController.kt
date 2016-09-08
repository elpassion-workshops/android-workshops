package com.elpassion.secretmessenger.conversation.add

class AddConversationController(val view: AddConversation.View,
                                val usersApi: AddConversation.UsersApi,
                                val api: AddConversation.AddApi) {

    fun onCreate() {
        view.showUsers(usersApi.getUsers())
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