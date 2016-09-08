package com.elpassion.secretmessenger.conversation.add

import rx.Subscription

class AddConversationController(val view: AddConversation.View,
                                val usersApi: AddConversation.UsersApi,
                                val addApi: AddConversation.AddApi) {

    var subscription: Subscription? = null

    fun onCreate() {
        subscription = usersApi.getUsers()
                .doOnSubscribe { view.showLoader() }
                .doOnUnsubscribe { view.hideLoader() }
                .subscribe({
                    view.showUsers(it)
                }, {
                    view.showError()
                })
    }

    fun onAddConversation(otherPersonEmail: String) {
        addApi.addConversation(otherPersonEmail)
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