package com.elpassion.secretmessenger.conversation.details

import com.elpassion.secretmessenger.common.Provider

interface ConversationDetails {
    interface Api {
        fun getConversation(s: String)

    }

    object ApiProvider : Provider<Api>({
        object : Api {
            override fun getConversation(s: String) {

            }
        }
    })

}