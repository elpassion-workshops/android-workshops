package com.elpassion.secretmessenger.conversation.add

import com.elpassion.secretmessenger.common.Provider
import rx.Observable

interface AddConversation {

    interface View {
        fun showUsers(users: List<String>)
        fun openConversationDetails(otherPersonEmail: String)
        fun showError()
    }

    interface Api {
        fun addConversation(): Observable<String>
    }

    object ApiProvider : Provider<Api>({
        throw UnsupportedOperationException("not implemented")
    })
}