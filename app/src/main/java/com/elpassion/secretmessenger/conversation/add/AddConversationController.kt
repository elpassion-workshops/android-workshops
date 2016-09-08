package com.elpassion.secretmessenger.conversation.add

class AddConversationController(val view: AddConversation.View,
                                val usersApi: AddConversation.UsersApi,
                                val addApi: AddConversation.AddApi) {

    fun onCreate() {
        usersApi.getUsers()
                .doOnSubscribe { view.showLoader() }
                .subscribe({
                    view.showUsers(it)
                }, {
                    view.showError()
                })
    }

    fun onAddConversation() {
        addApi.addConversation()
                .subscribe({
                    view.openConversationDetails(it)
                }, {
                    view.showError()
                })
    }
}