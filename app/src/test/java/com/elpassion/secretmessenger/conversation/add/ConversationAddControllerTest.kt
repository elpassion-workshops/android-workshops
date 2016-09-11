package com.elpassion.secretmessenger.conversation.add

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class ConversationAddControllerTest {
    @Test
    fun shouldCallApiOnCreate() {
        val api = mock<ConversationAdd.Api>()
        val conversationAddController = ConversationAddController(api)
        conversationAddController.onCreate()
        verify(api, times(1)).fetchUsers()
    }

}

interface ConversationAdd {
    interface Api {
        fun fetchUsers()
    }
}

class ConversationAddController(val api: ConversationAdd.Api) {
    fun onCreate() {
        api.fetchUsers()
    }

}
