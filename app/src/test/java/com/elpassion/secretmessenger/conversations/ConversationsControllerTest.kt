package com.elpassion.secretmessenger.conversations

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class ConversationsControllerTest {

    @Test
    fun shouldShowConversationsPlaceHolderOnCreateWhenAPIReturnsEmptyList() {
        val view = mock<ConversationsView>()
        val controller = ConversationsController(view)
        controller.onCreate()

        verify(view, times(1)).showConversationsPlaceholder()
    }

}

interface ConversationsView {
    fun showConversationsPlaceholder()
}

class ConversationsController(val view: ConversationsView) {
    fun onCreate() {
        view.showConversationsPlaceholder()
    }
}
