package com.elpassion.secretmessenger.conversation.list

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class ConversationListControllerTest {

    @Test
    fun shouldCallApiForConversationListOnCreate() {
        val api = mock<ConversationList.Api>()
        val view = mock<ConversationList.View>()
        val controller = ConversationListController(api, view)
        controller.onCreate()
        verify(api, times(1)).getUserConversationList()
    }

    @Test
    fun shouldShowErrorWhenCallToApiFails() {
        val api = mock<ConversationList.Api>()
        val view = mock<ConversationList.View>()
        val controller = ConversationListController(api, view)
        controller.onCreate()
        verify(view, times(1)).showError()
    }
}

class ConversationListController(val api: ConversationList.Api, val view: ConversationList.View) {
    fun onCreate() {
        api.getUserConversationList()
        view.showError()
    }
}

interface ConversationList {

    interface Api {
        fun getUserConversationList()
    }

    interface View {
        fun showError()
    }

}


