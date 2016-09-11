package com.elpassion.secretmessenger.conversation.add

import com.elpassion.secretmessenger.common.Provider
import rx.Observable

interface ConversationAdd {
    interface Api {
        fun fetchUsers(): Observable<List<User>>
    }

    interface View {
        fun showUsersList(listOf: List<User>)
        fun showError()
        fun showLoader()
        fun hideLoader()
        fun openConversation(user: User)
    }

    object ApiProvider : Provider<Api>({ throw NotImplementedError() })
}