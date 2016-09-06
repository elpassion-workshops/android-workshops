package com.elpassion.secretmessenger.conversations

import com.nhaarman.mockito_kotlin.*
import org.junit.Test

class ConversationsControllerTest {

    val view = mock<ConversationsView>()
    val api = mock<ConversationsApi>() {
        on { call() } doReturn emptyList<Conversation>()
    }
    val controller = ConversationsController(view, api)

    @Test
    fun shouldShowConversationsPlaceHolderOnCreateWhenAPIReturnsEmptyList() {
        controller.onCreate()

        verify(view, times(1)).showConversationsPlaceholder()
    }

    @Test
    fun shouldShowConversationsIfAPIReturnsData() {
        val conversations = listOf(Conversation())
        stubApiToReturn(conversations)

        controller.onCreate()

        verify(view, times(1)).showConversations(conversations)
    }

    private fun stubApiToReturn(conversations: List<Conversation>) {
        whenever(api.call()).thenReturn(conversations)
    }

}

interface ConversationsApi {
    fun call(): List<Conversation>

}

class Conversation {

}

interface ConversationsView {
    fun showConversationsPlaceholder()

    fun showConversations(listOf: List<Conversation>)
}

class ConversationsController(val view: ConversationsView, val api: ConversationsApi) {
    fun onCreate() {
        view.showConversationsPlaceholder()
        view.showConversations(api.call())
    }
}
