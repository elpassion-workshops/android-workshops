package com.elpassion.secretmessenger.conversation.details

import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import rx.Observable

class ConversationDetailsControllerTest {

    val friendId = "123"
    val view = mock<ConversationDetails.View>()
    val api = mock<ConversationDetails.Api>()
    val controller = ConversationDetailsController(view, api, friendId)

    @Test
    fun shouldShowMessagesReturnedFromApiOnCreate() {
        val messages = listOf(Message("text"))
        stubApiToReturn(messages)
        controller.onCreate()

        verify(view, times(1)).showMessages(messages)
    }

    @Test
    fun shouldShowErrorOnApiFail() {
        stubApiForError()
        controller.onCreate()

        verify(view, times(1)).showError()
    }

    @Test
    fun shouldNotShowErrorOnApiSuccess() {
        stubApiToReturn(emptyList())
        controller.onCreate()
        verify(view, never()).showError()
    }

    @Test
    fun shouldUpdateMessageListWhenNewMessageArrive() {
        val first = Message("first")
        val second = Message("second")
        stubApiToReturn(listOf(first, second))
        controller.onCreate()

        verify(view, times(1)).showMessages(listOf(first))
        verify(view, times(1)).showMessages(listOf(first, second))
    }

    @Test
    fun shouldSendNewMessageOnMessageSend() {
        val messageToSend = "New message"
        controller.onMessageSend(messageToSend)

        verify(api, times(1)).sendMessage(any(), eq(messageToSend))
    }

    @Test
    fun shouldInitViewOnCreate() {
        stubApiToReturn(emptyList())
        controller.onCreate()

        verify(view, times(1)).init()
    }

    @Test
    fun shouldSendFriendIdToApiOnMessageSend() {
        controller.onMessageSend("message")
        verify(api, times(1)).sendMessage(eq(friendId), any())
    }

    private fun stubApiToReturn(messages: List<Message>) {
        whenever(api.getMessages(any())).thenReturn(Observable.from(messages))
    }

    private fun stubApiForError() {
        whenever(api.getMessages(any())).thenReturn(Observable.error(RuntimeException()))
    }
}
