package com.elpassion.secretmessenger.conversation.list

import com.elpassion.secretmessenger.conversation.list.ConversationList.Api
import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import rx.Observable

class ConversationListControllerTest {

    val api = mock<Api>().stubToReturnList(emptyList())

    val view = mock<ConversationList.View>()
    val controller = ConversationListController(api, view)

    @Test
    fun shouldCallApiForConversationListOnCreate() {
        controller.onCreate()
        verify(api, times(1)).getUserConversationList()
    }

    @Test
    fun shouldShowErrorWhenCallToApiFails() {
        stubApiToReturnError()
        controller.onCreate()
        verify(view, times(1)).showError()
    }

    @Test
    fun shouldNotShowErrorWhenCallApiSucceed() {
        controller.onCreate()
        verify(view, never()).showError()
    }

    @Test
    fun shouldShowReturnedConversationList() {
        val conversationList = listOf(Conversation())
        api.stubToReturnList(conversationList)
        controller.onCreate()
        verify(view, times(1)).showConversationList(conversationList)
    }

    @Test
    fun shouldShowProgressIndicatorWhenCallToApiStarted() {
        controller.onCreate()
        verify(view, times(1)).showProgressIndicator()
    }

    private fun stubApiToReturnError() {
        whenever(api.getUserConversationList()).thenReturn(Observable.error(RuntimeException()))
    }

    private fun Api.stubToReturnList(conversationList: List<Conversation>): ConversationList.Api = apply {
        whenever(this.getUserConversationList()).thenReturn(Observable.just(conversationList))
    }
}


