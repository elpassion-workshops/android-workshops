package com.elpassion.secretmessenger.conversation.list

import rx.Subscription

class ConversationListController(val api: ConversationList.Api, val view: ConversationList.View) {

    var subscription: Subscription? = null

    fun onCreate() {
        subscription = api.getUserConversationList()
                .doOnSubscribe { view.showProgressIndicator() }
                .doOnUnsubscribe { view.hideProgressIndicator() }
                .subscribe({
                    view.showConversationList(it)
                }, {
                    view.showError()
                })
    }

    fun onDestroy() {
        subscription?.unsubscribe()
    }

    fun showConversation(conversationId: String) {
        view.showConversationDetails(conversationId)
    }
}