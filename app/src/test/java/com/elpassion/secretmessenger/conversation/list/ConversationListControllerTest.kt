package com.elpassion.secretmessenger.conversation.list

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class ConversationListControllerTest {

    @Test
    fun shouldCallApiForConversationListOnCreate() {
        val api = mock<ConversationList.Api>()
        val controller = ConversationListController(api)
        controller.onCreate()
        verify(api, times(1)).getUserConversationList()
    }
}

class ConversationListController(val api: ConversationList.Api) {
    fun onCreate() {
        api.getUserConversationList()
    }

}

interface ConversationList {

    interface Api {
        fun getUserConversationList()
    }

}


