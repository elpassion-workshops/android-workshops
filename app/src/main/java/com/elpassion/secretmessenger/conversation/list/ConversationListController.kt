package com.elpassion.secretmessenger.conversation.list

class ConversationListController(val api: ConversationList.Api, val view: ConversationList.View) {
    fun onCreate() {
        api.getUserConversationList()
                .doOnSubscribe { view.showProgressIndicator() }
                .subscribe({
                    view.showConversationList(it)
                }, {
                    view.showError()
                })
    }
}