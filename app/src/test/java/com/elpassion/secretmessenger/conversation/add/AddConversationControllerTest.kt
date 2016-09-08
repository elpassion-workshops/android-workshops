package com.elpassion.secretmessenger.conversation.add

import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import rx.Observable

class AddConversationControllerTest {

    val view = mock<AddConversation.View>()
    val api = mock<AddConversation.Api>()
    val controller = AddConversationController(view, api)

    @Test
    fun shouldOpenConversationDetailsOnConversationAdd() {
        stubApiToPass()
        controller.onAddConversation()
        verify(view, times(1)).openConversationDetails(any())
    }

    @Test
    fun shouldNotOpenConversationDetailsWhenAddingConversationFails() {
        stubApiToFail()
        controller.onAddConversation()
        verify(view, never()).openConversationDetails(any())
    }

    @Test
    fun shouldPassConversationUuidFromApiToView() {
        stubApiToPass("otherPersonEmail")
        controller.onAddConversation()
        verify(view, times(1)).openConversationDetails("otherPersonEmail")
    }

    @Test
    fun shouldShowErrorWhenAddingConversationFails() {
        stubApiToFail()
        controller.onAddConversation()
        verify(view, times(1)).showError()
    }

    @Test
    fun shouldNotShowErrorWhenAddingConversationSucceed() {
        stubApiToPass()
        controller.onAddConversation()
        verify(view, never()).showError()
    }

    private fun stubApiToPass(conversationUuid: String = "") {
        whenever(api.addConversation()).thenReturn(Observable.just(conversationUuid))
    }

    private fun stubApiToFail() {
        whenever(api.addConversation()).thenReturn(Observable.error(RuntimeException()))
    }
}