package com.elpassion.secretmessenger.conversations

import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import rx.Observable

class ConversationsControllerTest {

    val view = mock<ConversationsView>()
    val api = mock<ConversationsApi>() {
        on { call() } doReturn Observable.just(emptyList<Conversation>())
    }
    val controller = ConversationsController(view, api)

    @Test
    fun shouldShowConversationsPlaceHolderOnCreateWhenAPIReturnsEmptyList() {
        controller.onCreate()

        verify(view, times(1)).showConversationsPlaceholder()
    }

    @Test
    fun shouldShowConversationsIfAPIReturnsData() {
        val conversations = listOf(Conversation())

        stubApiAndFireOnCreate(conversations)

        verify(view, times(1)).showConversations(conversations)
    }

    @Test
    fun shouldNotShowConversationsPlaceholderOnCreateWhenApiReturnsSomeData() {
        val conversations = listOf(Conversation())

        stubApiAndFireOnCreate(conversations)

        verify(view, never()).showConversationsPlaceholder()
    }

    @Test
    fun shouldShowErrorWhenApiReturnsErrorOnCreate() {
        stubApiToReturnError()

        controller.onCreate()

        verify(view, times(1)).showError()
    }

    @Test
    fun shouldShowLoaderWhileCallingApi() {
        stubApiAndFireOnCreate(listOf(Conversation()))

        verify(view, times(1)).showLoader()
    }

    private fun stubApiToReturnError() {
        whenever(api.call()).thenReturn(Observable.error(RuntimeException()))
    }

    private fun stubApiToReturn(conversations: List<Conversation>) {
        whenever(api.call()).thenReturn(Observable.just(conversations))
    }

    private fun stubApiAndFireOnCreate(conversations: List<Conversation>) {
        stubApiToReturn(conversations)

        controller.onCreate()
    }

}

interface ConversationsApi {
    fun call(): Observable<List<Conversation>>

}

class Conversation {

}

interface ConversationsView {
    fun showConversationsPlaceholder()

    fun showConversations(listOf: List<Conversation>)

    fun showError()

    fun showLoader()
}

class ConversationsController(val view: ConversationsView, val api: ConversationsApi) {
    fun onCreate() {
        api.call()
                .doOnSubscribe { view.showLoader() }
                .subscribe(onSuccess, onError)
    }

    val onSuccess: (List<Conversation>) -> Unit = { conversations ->
        if (conversations.isEmpty()) {
            view.showConversationsPlaceholder()
        } else {
            view.showConversations(conversations)
        }
    }

    val onError: (Throwable) -> Unit = {
        view.showError()
    }
}
