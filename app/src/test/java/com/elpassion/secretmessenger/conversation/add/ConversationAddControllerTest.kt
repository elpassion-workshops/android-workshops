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
        val list = listOf<User>(User())
        stubApiToReturn(list)
        controller.onCreate()
        verify(view, times(1)).showUsersList(list)
    }

    @Test
    fun shouldNotShowUsersListOnApiFail() {
        stubApiToReturnError()
        controller.onCreate()
        verify(view, never()).showUsersList(any())
    }

    @Test
    fun shouldShowErrorOnApiFail() {
        stubApiToReturnError()
        controller.onCreate()
        verify(view, times(1)).showError()
    }
    @Test
    fun shouldShowLoaderWhenCallingApi() {
        controller.onCreate()
        verify(view, times(1)).showLoader()
    }

    private fun stubApiToReturn(list: List<User>) {
        whenever(api.fetchUsers()).thenReturn(Observable.just(list))
    }

    private fun stubApiToReturnError() {
        whenever(api.fetchUsers()).thenReturn(Observable.error(RuntimeException()))
    }
}

class User

interface ConversationAdd {
    interface Api {
        fun fetchUsers(): Observable<List<User>>
    }

    interface View {
        fun showUsersList(listOf: List<User>)
        fun showError()

        fun showLoader()
    }
}

class ConversationAddController(val api: ConversationAdd.Api, val view: ConversationAdd.View) {
    fun onCreate() {
        view.showLoader()
        api.fetchUsers().subscribe({ users ->
            view.showUsersList(users)
        }, {
            view.showError()
        })
    }
}