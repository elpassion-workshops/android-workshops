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
        whenever(api.getMessages()).thenReturn(Observable.just(emptyList()))
        controller.onCreate()

        verify(view, times(1)).showMessages(any())
    }

    @Test
    fun shouldShowMessagesReturnedFromApiOnCreate() {
        val messages = listOf(Message("text"))
        whenever(api.getMessages()).thenReturn(Observable.just(messages))
        controller.onCreate()

        verify(view, times(1)).showMessages(messages)
    }

    @Test
    fun shouldShowErrorOnApiFail() {
        whenever(api.getMessages()).thenReturn(Observable.error(RuntimeException()))
        controller.onCreate()

        verify(view, times(1)).showError()
    }

    @Test
    fun shouldNotShowErrorOnApiSuccess() {
        whenever(api.getMessages()).thenReturn(Observable.just(emptyList()))
        controller.onCreate()
        verify(view, never()).showError()
    }
}

interface ConversationDetails {
    interface View {
        fun showMessages(messages: List<Message>)
        fun showError()
    }

    interface Api {
        fun getMessages(): Observable<List<Message>>
    }
}

data class Message(val text: String)

class ConversationDetailsController(val view: ConversationDetails.View, val api: ConversationDetails.Api) {
    fun onCreate() {
        api.getMessages().subscribe({
            view.showMessages(it)
        }, {
            view.showError()
        })
    }
}
