package com.elpassion.secretmessenger.conversation.add

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class ConversationAddControllerTest {
    private val api = mock<ConversationAdd.Api>()
    private val view = mock<ConversationAdd.View>()
    private val controller = ConversationAddController(api, view)

    @Test
    fun shouldCallApiOnCreate() {
        controller.onCreate()
        verify(api, times(1)).fetchUsers()
    }

    @Test
    fun shouldShowUsersListFromApi() {
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
