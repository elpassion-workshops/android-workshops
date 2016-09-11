package com.elpassion.secretmessenger.conversation.add

class ConversationAddController(val api: ConversationAdd.Api, val view: ConversationAdd.View) : OnUserListener {
    fun onCreate() {
        view.showLoader()
        api.fetchUsers().subscribe({ users ->
            view.showUsersList(users, this)
        }, {
            view.showError()
        }, {
            view.hideLoader()
        })
    }

    override fun onUser(user: User) {
        view.openConversation(user)
    }
}

interface OnUserListener {
    fun onUser(user: User)
}
