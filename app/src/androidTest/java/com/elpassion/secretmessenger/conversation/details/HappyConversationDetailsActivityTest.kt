package com.elpassion.secretmessenger.conversation.details

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.*
import com.elpassion.secretmessenger.R
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Rule
import org.junit.Test
import rx.Observable

class HappyConversationDetailsActivityTest {

    val api = mock<ConversationDetails.Api>() {
        on { getMessages() } doReturn Observable.just(Message("message"))
    }

    @JvmField @Rule
    val rule = object : ActivityTestRule<ConversationDetailsActivity>(ConversationDetailsActivity::class.java) {
        override fun beforeActivityLaunched() {
            ConversationDetails.ApiProvider.override = { api }
        }
    }

    @Test
    fun shouldShowContainerForMessages() {
        onId(R.id.messagesContainer).isDisplayed()
    }

    @Test
    fun shouldDisplayMessagesFromApiInContainer() {
        onId(R.id.messagesTextView).isDisplayed()
    }

    @Test
    fun shouldNotShowErrorMessageIfApiReturnsSuccess() {
        onText(R.string.conversations_details_api_error).isNotDisplayed()
    }

    @Test
    fun shouldSendMessageToApiAfterClickOnSendButton() {
        onId(R.id.sendButton).click()

        verify(api).sendMessage(any(), any())
    }
}

