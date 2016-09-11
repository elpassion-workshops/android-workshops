package com.elpassion.secretmessenger.conversation.add

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class ConversationAddControllerTest {
    @Test
    fun shouldCallApiOnCreate() {
        val api = mock<ConversationAdd.Api>()
        val view = mock<ConversationAdd.View>()
        val conversationAddController = ConversationAddController(api, view)
        conversationAddController.onCreate()
        verify(api, times(1)).fetchUsers()
    }

    @Test
    fun shouldShowUsersListFromApi() {
        val api = mock<ConversationAdd.Api>()
        val view = mock<ConversationAdd.View>()
        val controller = ConversationAddController(api, view)
        controller.onCreate()
        verify(view, times(1)).showUsersList()
    }
}

interface ConversationAdd {
    interface Api {
        fun fetchUsers()
    }

    interface View {
        fun showUsersList()

    }
}

class ConversationAddController(val api: ConversationAdd.Api, val view: ConversationAdd.View) {
    fun onCreate() {
        api.fetchUsers()
        view.showUsersList()
    }

}
