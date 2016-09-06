package com.elpassion.secretmessenger.conversations

class ConversationsController(val view: Conversations.View, val api: Conversations.Api) {
    fun onCreate() {
        api.call()
                .doOnSubscribe { view.showLoader() }
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
}