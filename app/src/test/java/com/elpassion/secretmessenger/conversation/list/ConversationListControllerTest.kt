package com.elpassion.secretmessenger.conversation.list

import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import rx.Observable

class ConversationListControllerTest {

    val api = mock<ConversationList.Api>() .apply{
        whenever(getUserConversationList()).thenReturn(Observable.just(Unit))
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
}

class ConversationListController(val api: ConversationList.Api, val view: ConversationList.View) {
    fun onCreate() {
        api.getUserConversationList().subscribe({

        }, {
            view.showError()
        })
    }
}

interface ConversationList {

    interface Api {
        fun getUserConversationList() : Observable<Unit>
    }

    interface View {
        fun showError()
    }

}


