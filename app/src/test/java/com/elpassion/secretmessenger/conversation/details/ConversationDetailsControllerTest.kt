package com.elpassion.secretmessenger.conversation.details

import com.nhaarman.mockito_kotlin.*
import org.junit.Test


class ConversationDetailsControllerTest {

    @Test
    fun shouldShowMessagesOnCreate() {
        val view = mock<ConversationDetails.View>()
        val api = mock<ConversationDetails.Api>()
        whenever(api.getMessages()).thenReturn(emptyList())
        val controller = ConversationDetailsController(view, api)
        controller.onCreate()

        verify(view, times(1)).showMessages(any())
    }

    @Test
    fun shouldShowMessagesReturnedFromApiOnCreate() {
        val view = mock<ConversationDetails.View>()
        val api = mock<ConversationDetails.Api>()
        val messages = listOf(Message("text"))
        val controller = ConversationDetailsController(view, api)
        whenever(api.getMessages()).thenReturn(messages)
        controller.onCreate()

        verify(view, times(1)).showMessages(messages)
    }
}

interface ConversationDetails {
    interface View {
        fun showMessages(messages: List<Message>)
    }

    interface Api {
        fun getMessages(): List<Message>

    }
}

data class Message(val text: String) {

}

class ConversationDetailsController(val view: ConversationDetails.View, val api: ConversationDetails.Api) {
    fun onCreate() {
        view.showMessages(api.getMessages())

    }
}
