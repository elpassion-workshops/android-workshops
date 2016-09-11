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

    @Test
    fun shouldHideLoaderWhenApiCallComplete() {
        controller.onCreate()
        verify(view, times(1)).hideLoader()
    }

    private fun stubApiToReturn(list: List<User>) {
        whenever(api.fetchUsers()).thenReturn(Observable.just(list))
    }

    private fun stubApiToReturnError() {
        whenever(api.fetchUsers()).thenReturn(Observable.error(RuntimeException()))
    }
}