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

    private fun stubApiToReturnError() {
        whenever(api.getUserConversationList()).thenReturn(Observable.error(RuntimeException()))
    }

    private fun Api.stubToReturnList(conversationList: List<Conversation>): ConversationList.Api = apply {
        whenever(this.getUserConversationList()).thenReturn(Observable.just(conversationList))
    }
}

class ConversationListController(val api: Api, val view: ConversationList.View) {
    fun onCreate() {
        api.getUserConversationList().subscribe({
            view.showConversationList(it)
        }, {
            view.showError()
        })
    }
}

interface ConversationList {

    interface Api {
        fun getUserConversationList(): Observable<List<Conversation>>
    }

    interface View {
        fun showError()
        fun showConversationList(conversationList: List<Conversation>)
    }

}

class Conversation {

}


