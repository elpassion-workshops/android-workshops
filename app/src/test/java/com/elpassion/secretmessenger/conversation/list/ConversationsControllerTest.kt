package com.elpassion.secretmessenger.conversation.list

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
        val conversations = listOf(Conversation(id = "1", otherPersonName = "otherName"))
        stubApiAndFireOnCreate(conversations)
        verify(view, times(1)).showConversations(conversations, controller)
    }

    @Test
    fun shouldNotShowConversationsPlaceholderOnCreateWhenApiReturnsSomeData() {
        stubApiAndFireOnCreate()
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
        stubApiAndFireOnCreate()
        verify(view, times(1)).showLoader()
    }

    @Test
    fun shouldHideLoaderWhenCallToApiEnds() {
        stubApiAndFireOnCreate()
        verify(view, times(1)).hideLoader()
    }

    @Test
    fun shouldHideLoaderOnDestroy() {
        stubApiToReturnNever()
        controller.onCreate()
        controller.onDestroy()
        verify(view, times(1)).hideLoader()
    }

    @Test
    fun shouldNotHideLoaderOnDestroyWhenSubscriptionIsNull() {
        controller.onDestroy()
        verify(view, never()).hideLoader()
    }

    @Test
    fun shouldOpenConversationScreenOnConversation() {
        val conversationUuid = "1"
        controller.onConversation(conversationUuid)
        verify(view, times(1)).openConversationScreen(conversationUuid)
    }

    @Test
    fun shouldOpenAddConversationScreenOnAddConversation() {
        controller.onAddConversation()
        verify(view).openAddConversationScreen()
    }

    private fun stubApiToReturnNever() {
        whenever(api.call()).thenReturn(Observable.never())
    }

    private fun stubApiToReturnError() {
        whenever(api.call()).thenReturn(Observable.error(RuntimeException()))
    }

    private fun stubApiToReturn(conversations: List<Conversation>) {
        whenever(api.call()).thenReturn(Observable.just(conversations))
    }

    private fun stubApiAndFireOnCreate(conversations: List<Conversation> = listOf(Conversation(id = "1", otherPersonName = "otherName"))) {
        stubApiToReturn(conversations)
        controller.onCreate()
    }
}
