package com.elpassion.secretmessenger.conversation.details

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test


class ConversationDetailsControllerTest {

    @Test
    fun shouldShowMessagesOnCreate() {
        val view = mock<ConversationDetails.View>()
        val controller = ConversationDetailsController(view)
        controller.onCreate()

        verify(view, times(1)).showMessages()
    }
}

interface ConversationDetails {
    interface View {
        fun showMessages()
    }
}

class ConversationDetailsController(val view: ConversationDetails.View) {
    fun onCreate() {
        view.showMessages()
    }
}
