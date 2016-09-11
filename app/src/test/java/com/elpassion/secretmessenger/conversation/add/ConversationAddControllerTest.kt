package com.elpassion.secretmessenger.conversation.add

import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import rx.Observable

class ConversationAddControllerTest {
    private val api = mock<ConversationAdd.Api>() {
        on { fetchUsers() } doReturn Observable.just(Unit)
    }
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

    @Test
    fun shouldNotShowUsersListOnApiFail() {
        stubApiToReturnError()
        controller.onCreate()
        verify(view, never()).showUsersList()
    }

    private fun stubApiToReturnError() {
        whenever(api.fetchUsers()).thenReturn(Observable.error(RuntimeException()))
    }
}

interface ConversationAdd {
    interface Api {
        fun fetchUsers(): Observable<Unit>
    }

    interface View {
        fun showUsersList()
    }
}

class ConversationAddController(val api: ConversationAdd.Api, val view: ConversationAdd.View) {
    fun onCreate() {
        api.fetchUsers().subscribe({
            view.showUsersList()
        }, {

        })

    }

}
