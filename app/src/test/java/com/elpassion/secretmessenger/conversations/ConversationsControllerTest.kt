package com.elpassion.secretmessenger.conversations

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test

class ConversationsControllerTest {

    val api = mock<ConversationsApi>().apply {
        whenever(this.call()).thenReturn(emptyList())
    }

    @Test
    fun shouldShowConversationsPlaceHolderOnCreateWhenAPIReturnsEmptyList() {
        val view = mock<ConversationsView>()
        val controller = ConversationsController(view, api)
        controller.onCreate()

        verify(view, times(1)).showConversationsPlaceholder()
    }

    @Test
    fun shouldShowConversationsIfAPIReturnsData() {
        val conversations = listOf(Conversation())
        whenever(api.call()).thenReturn(conversations)
        val view = mock<ConversationsView>()
        val controller = ConversationsController(view, api)

        controller.onCreate()

        verify(view, times(1)).showConversations(conversations)
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
