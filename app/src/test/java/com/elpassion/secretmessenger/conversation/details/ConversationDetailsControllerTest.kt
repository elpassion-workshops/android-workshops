package com.elpassion.secretmessenger.conversation.details

import com.nhaarman.mockito_kotlin.*
import org.junit.Test


class ConversationDetailsControllerTest {

    val view = mock<ConversationDetails.View>()
    val api = mock<ConversationDetails.Api>()
    val controller = ConversationDetailsController(view, api)

    @Test
    fun shouldShowMessagesOnCreate() {
        whenever(api.getMessages()).thenReturn(emptyList())
        controller.onCreate()

        verify(view, times(1)).showMessages(any())
    }

    @Test
    fun shouldShowMessagesReturnedFromApiOnCreate() {
        val messages = listOf(Message("text"))
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
