package com.elpassion.secretmessenger.conversation.add

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class AddConversationControllerTest {

    val view = mock<AddConversation.View>()
    val controller = AddConversationController(view)

    @Test
    fun shouldOpenConversationDetailsOnConversationAdd() {
        controller.onAddConversation()
        verify(view, times(1)).openConversationDetails(any())
    }
}