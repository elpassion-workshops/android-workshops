package com.elpassion.secretmessenger.conversation.list

import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import rx.Observable

class ConversationListControllerTest {

    val api = mock<ConversationList.Api>() .apply{
        whenever(getUserConversationList()).thenReturn(Observable.just(emptyList()))
    }
    val view = mock<ConversationList.View>()
    val controller = ConversationListController(api, view)

    @Test
    fun shouldCallApiForConversationListOnCreate() {
        controller.onCreate()
        verify(api, times(1)).getUserConversationList()
    }

    @Test
    fun shouldShowErrorWhenCallToApiFails() {
        whenever(api.getUserConversationList()).thenReturn(Observable.error(RuntimeException()))
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
        whenever(api.getUserConversationList()).thenReturn(Observable.just(conversationList))
        controller.onCreate()
        verify(view, times(1)).showConversationList(conversationList)
    }
}

class ConversationListController(val api: ConversationList.Api, val view: ConversationList.View) {
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
        fun getUserConversationList() : Observable<List<Conversation>>
    }

    interface View {
        fun showError()
        fun showConversationList(conversationList: List<Conversation>)
    }

}

class Conversation {

}


