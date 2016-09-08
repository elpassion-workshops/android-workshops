package com.elpassion.secretmessenger.conversation.add

import rx.Subscription

class AddConversationController(val view: AddConversation.View,
                                val usersApi: AddConversation.UsersApi,
                                val addApi: AddConversation.AddApi) : OnAddConversationListener {

    var subscription: Subscription? = null

    fun onCreate() {
        subscription = usersApi.getUsers()
                .doOnSubscribe { view.showLoader() }
                .doOnUnsubscribe { view.hideLoader() }
                .subscribe({userEmail ->
                    view.showUsers(userEmail, this)
                }, {
                    view.showError()
                })
    }

    override fun onAddConversation(otherPersonEmail: String) {
        subscription?.unsubscribe()
        subscription = addApi.addConversation(otherPersonEmail)
                .doOnSubscribe { view.showLoader() }
                .doOnUnsubscribe { view.hideLoader() }
                .subscribe({
                    view.openConversationDetails(it)
                }, {
                    view.showError()
                })
    }

    fun onDestroy() {
        subscription?.unsubscribe()
    }
}

interface OnAddConversationListener {
    fun onAddConversation(otherPersonEmail: String)
}
