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
        controller.onAddConversation("email@pl.pl")
        verify(view, times(1)).openConversationDetails(any())
    }

    @Test
    fun shouldNotOpenConversationDetailsWhenAddingConversationFails() {
        stubAddApiToFail()
        controller.onAddConversation("email@pl.pl")
        verify(view, never()).openConversationDetails(any())
    }

    @Test
    fun shouldPassConversationUuidFromApiToView() {
        stubAddApiToPass("otherPersonEmail")
        controller.onAddConversation("email@pl.pl")
        verify(view, times(1)).openConversationDetails("otherPersonEmail")
    }

    @Test
    fun shouldShowErrorWhenAddingConversationFails() {
        stubAddApiToFail()
        controller.onAddConversation("email@pl.pl")
        verify(view, times(1)).showError()
    }

    @Test
    fun shouldNotShowErrorWhenAddingConversationSucceed() {
        stubAddApiToPass()
        controller.onAddConversation("email@pl.pl")
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

    @Test
    fun shouldHideLoaderOnDestroy() {
        stubUsersApiToReturnNever()
        controller.onCreate()
        controller.onDestroy()
        verify(view, times(1)).hideLoader()
    }

    @Test
    fun shouldHideLoaderOnCallFinished() {
        stubUsersApiToReturn(emptyList())
        controller.onCreate()
        verify(view, times(1)).hideLoader()
    }

    @Test
    fun shouldPassSelectedUserToAddApi() {
        val email = "email@pl.pl"
        whenever(addApi.addConversation(email)).thenReturn(Observable.just(email))
        controller.onAddConversation(email)
        verify(view, times(1)).openConversationDetails(email)
    }

    @Test
    fun shouldReallyPassSelectedUserToAddApi() {
        val email = "emailemail@pl.pl"
        whenever(addApi.addConversation(email)).thenReturn(Observable.just(email))
        controller.onAddConversation(email)
        verify(view, times(1)).openConversationDetails(email)
    }

    @Test
    fun shouldShowLoaderWhenCallToAddApiStarts() {
        stubAddApiToReturnNever()
        controller.onAddConversation("")
        verify(view, times(1)).showLoader()
    }

    @Test
    fun shouldHideLoaderOnDestroyWhenCallToApiIsNotNull() {
        stubAddApiToReturnNever()
        controller.onAddConversation("")
        controller.onDestroy()
        verify(view, times(1)).hideLoader()
    }

    @Test
    fun shouldShowLoaderTwiceAndHideOnlyOnceIfThereAreTwoCallsToApi() {
        stubAddApiToReturnNever()
        controller.onAddConversation("")
        controller.onAddConversation("")
        verify(view, times(1)).hideLoader()
        verify(view, times(2)).showLoader()
    }

    private fun stubAddApiToReturnNever() = whenever(addApi.addConversation(any())).thenReturn(Observable.never())

    private fun stubUsersApiToReturnNever() = whenever(usersApi.getUsers()).thenReturn(Observable.never())

    private fun stubUsersApiToReturnError() = whenever(usersApi.getUsers()).thenReturn(Observable.error(RuntimeException()))

    private fun stubUsersApiToReturn(users: List<String>) = whenever(usersApi.getUsers()).thenReturn(Observable.just(users))

    private fun stubAddApiToPass(conversationUuid: String = "") = whenever(addApi.addConversation(any())).thenReturn(Observable.just(conversationUuid))

    private fun stubAddApiToFail() = whenever(addApi.addConversation("email@pl.pl")).thenReturn(Observable.error(RuntimeException()))

}