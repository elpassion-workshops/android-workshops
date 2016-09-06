package com.elpassion.secretmessenger.conversations

import rx.Subscription

class ConversationsController(val view: Conversations.View, val api: Conversations.Api) {

    private var subscription: Subscription? = null

    fun onCreate() {
        subscription = api.call()
                .doOnSubscribe { view.showLoader() }
                .doOnUnsubscribe { view.hideLoader() }
                .subscribe(onSuccess, onError)
    }

    val onSuccess: (List<Conversation>) -> Unit = { conversations ->
        if (conversations.isEmpty()) {
            view.showConversationsPlaceholder()
        } else {
            view.showConversations(conversations)
        }
    }

    val onError: (Throwable) -> Unit = {
        view.showError()
    }

    fun onDestroy() {
        subscription?.unsubscribe()
    }

    fun onConversation(conversationUuid: String) {
        view.openConversationScreen(conversationUuid)
    }

    fun onAddConversation() {
        view.openAddConversationScreen()
    }
}