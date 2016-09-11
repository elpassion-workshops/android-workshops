package com.elpassion.secretmessenger.conversation.list

import com.elpassion.secretmessenger.common.Provider
import rx.Observable

interface ConversationList {

    interface Api {
        fun getUserConversationList() : Observable<List<Conversation>>
    }

    interface View {
        fun showError()
        fun showConversationList(conversationList: List<Conversation>)
        fun showProgressIndicator()
        fun hideProgressIndicator()
        fun showConversationDetails()
    }

    object ApiProvider : Provider<ConversationList.Api>({ FirebaseConversationListApi() })


}