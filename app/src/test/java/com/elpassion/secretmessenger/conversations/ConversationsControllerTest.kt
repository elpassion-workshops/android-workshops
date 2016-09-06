package com.elpassion.secretmessenger.conversations

import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import rx.Observable

class ConversationsControllerTest {

    val view = mock<Conversations.View>()
    val api = mock<Conversations.Api>() {
        on { call() } doReturn Observable.just(emptyList<Conversation>())
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

        stubApiAndFireOnCreate(conversations)

        verify(view, times(1)).showConversations(conversations)
    }

    @Test
    fun shouldNotShowConversationsPlaceholderOnCreateWhenApiReturnsSomeData() {
        val conversations = listOf(Conversation())

        stubApiAndFireOnCreate(conversations)

        verify(view, never()).showConversationsPlaceholder()
    }

    @Test
    fun shouldShowErrorWhenApiReturnsErrorOnCreate() {
        stubApiToReturnError()

        controller.onCreate()

        verify(view, times(1)).showError()
    }

    @Test
    fun shouldShowLoaderWhileCallingApi() {
        stubApiAndFireOnCreate(listOf(Conversation()))

        verify(view, times(1)).showLoader()
    }

    @Test
    fun shouldHideLoaderWhenCallToApiEnds() {
        stubApiAndFireOnCreate(listOf(Conversation()))

        verify(view, times(1)).hideLoader()
    }

    private fun stubApiToReturnError() {
        whenever(api.call()).thenReturn(Observable.error(RuntimeException()))
    }

    private fun stubApiToReturn(conversations: List<Conversation>) {
        whenever(api.call()).thenReturn(Observable.just(conversations))
    }

    private fun stubApiAndFireOnCreate(conversations: List<Conversation>) {
        stubApiToReturn(conversations)

        controller.onCreate()
    }

}
