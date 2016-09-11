package com.elpassion.secretmessenger.conversation.add

class ConversationAddController(val api: ConversationAdd.Api, val view: ConversationAdd.View) {
    fun onCreate() {
        view.showLoader()
        api.fetchUsers().subscribe({ users ->
            view.showUsersList(users)
        }, {
            view.showError()
        }, {
            view.hideLoader()
        })
    }

    fun onUser(user: User) {
        view.openConversation(user)
    }
}