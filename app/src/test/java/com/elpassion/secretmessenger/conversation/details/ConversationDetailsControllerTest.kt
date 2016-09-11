package com.elpassion.secretmessenger.conversation.details

import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import rx.Observable

class ConversationDetailsControllerTest {

    val view = mock<ConversationDetails.View>()
    val api = mock<ConversationDetails.Api>()
    val controller = ConversationDetailsController(view, api)

    @Test
    fun shouldShowMessagesOnCreate() {
        stubApiToReturn(emptyList())
        controller.onCreate()

        verify(view, times(1)).showMessages(any())
    }

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

    private fun stubApiToReturn(messages: List<Message>) {
        whenever(api.getMessages()).thenReturn(Observable.just(messages))
    }

    private fun stubApiForError() {
        whenever(api.getMessages()).thenReturn(Observable.error(RuntimeException()))
    }
}
