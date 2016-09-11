package com.elpassion.secretmessenger.conversation.add

import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import rx.Observable

class ConversationAddControllerTest {
    private val api = mock<ConversationAdd.Api>() {
        on { fetchUsers() } doReturn Observable.just(emptyList())
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
        val list: List<User> = listOf<User>(User())
        whenever(api.fetchUsers()).thenReturn(Observable.just(list))
        controller.onCreate()
        verify(view, times(1)).showUsersList(list)
    }

    @Test
    fun shouldNotShowUsersListOnApiFail() {
        stubApiToReturnError()
        controller.onCreate()
        verify(view, never()).showUsersList(any())
    }


    private fun stubApiToReturnError() {
        whenever(api.fetchUsers()).thenReturn(Observable.error(RuntimeException()))
    }
}

class User {

}

interface ConversationAdd {
    interface Api {
        fun fetchUsers(): Observable<List<User>>
    }

    interface View {
        fun showUsersList(listOf: List<User>)
    }
}

class ConversationAddController(val api: ConversationAdd.Api, val view: ConversationAdd.View) {
    fun onCreate() {
        api.fetchUsers().subscribe({ users ->
            view.showUsersList(users)
        }, {

        })

    }

}
