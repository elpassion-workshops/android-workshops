package com.elpassion.secretmessenger.conversation.add

import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import rx.Observable

class AddConversationControllerTest {

    val view = mock<AddConversation.View>()
    val addApi = mock<AddConversation.AddApi>()
    val usersApi = mock<AddConversation.UsersApi>()
    val controller = AddConversationController(view, usersApi, addApi)

    @Test
    fun shouldOpenConversationDetailsOnConversationAdd() {
        stubAddApiToPass()
        controller.onAddConversation()
        verify(view, times(1)).openConversationDetails(any())
    }

    @Test
    fun shouldNotOpenConversationDetailsWhenAddingConversationFails() {
        stubAddApiToFail()
        controller.onAddConversation()
        verify(view, never()).openConversationDetails(any())
    }

    @Test
    fun shouldPassConversationUuidFromApiToView() {
        stubAddApiToPass("otherPersonEmail")
        controller.onAddConversation()
        verify(view, times(1)).openConversationDetails("otherPersonEmail")
    }

    @Test
    fun shouldShowErrorWhenAddingConversationFails() {
        stubAddApiToFail()
        controller.onAddConversation()
        verify(view, times(1)).showError()
    }

    @Test
    fun shouldNotShowErrorWhenAddingConversationSucceed() {
        stubAddApiToPass()
        controller.onAddConversation()
        verify(view, never()).showError()
    }

    @Test
    fun shouldShowAvailableUsersListOnCreate() {
        stubUsersApiToReturn(emptyList())
        controller.onCreate()
        verify(view, times(1)).showUsers(any())
    }

    @Test
    fun shouldShowUsersDependingOnApiResult() {
        val users = listOf("user1", "user2")
        stubUsersApiToReturn(users)
        controller.onCreate()
        verify(view, times(1)).showUsers(users)
    }

    @Test
    fun shouldShowErrorWhenUsersApiFails() {
        stubUsersApiToReturnError()
        controller.onCreate()
        verify(view, times(1)).showError()
        verify(view, never()).showUsers(any())
    }

    @Test
    fun shouldShowLoaderWhenApiCallIsFired() {
        stubUsersApiToReturn(emptyList())
        controller.onCreate()
        verify(view, times(1)).showLoader()
    }

    private fun stubUsersApiToReturnError() = whenever(usersApi.getUsers()).thenReturn(Observable.error(RuntimeException()))

    private fun stubUsersApiToReturn(users: List<String>) = whenever(usersApi.getUsers()).thenReturn(Observable.just(users))

    private fun stubAddApiToPass(conversationUuid: String = "") = whenever(addApi.addConversation()).thenReturn(Observable.just(conversationUuid))

    private fun stubAddApiToFail() = whenever(addApi.addConversation()).thenReturn(Observable.error(RuntimeException()))

}