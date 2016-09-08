package com.elpassion.secretmessenger.conversation.add

import com.elpassion.secretmessenger.common.Provider
import rx.Observable

interface AddConversation {

    interface View {
        fun showUsers(users: List<String>)
        fun openConversationDetails(otherPersonEmail: String)
        fun showError()
        fun showLoader()
        fun hideLoader()
    }

    interface AddApi {
        fun addConversation(otherPersonEmail: String): Observable<String>
    }

    object ApiProvider : Provider<AddApi>({
        throw UnsupportedOperationException("not implemented")
    })

    interface UsersApi {
        fun getUsers(): Observable<List<String>>
    }

    object UsersApiProvider : Provider<UsersApi>({
        throw UnsupportedOperationException("not implemented")
    })
}