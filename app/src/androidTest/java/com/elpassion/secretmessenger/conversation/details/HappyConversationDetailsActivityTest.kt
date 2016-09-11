package com.elpassion.secretmessenger.conversation.details

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.*
import com.elpassion.secretmessenger.R
import com.nhaarman.mockito_kotlin.*
import org.junit.Rule
import org.junit.Test
import rx.Observable

class HappyConversationDetailsActivityTest {

    val api = mock<ConversationDetails.Api>() {
        on { getMessages() } doReturn Observable.just(Message("message"))
    }

    @JvmField @Rule
    val rule = object : ActivityTestRule<ConversationDetailsActivity>(ConversationDetailsActivity::class.java, false, false) {
        override fun beforeActivityLaunched() {
            ConversationDetails.ApiProvider.override = { api }
        }
    }

    @Test
    fun shouldShowContainerForMessages() {
        startActivity()
        onId(R.id.messagesContainer).isDisplayed()
    }

    @Test
    fun shouldDisplayMessagesFromApiInContainer() {
        startActivity()
        onId(R.id.messagesTextView).isDisplayed()
    }

    @Test
    fun shouldNotShowErrorMessageIfApiReturnsSuccess() {
        startActivity()
        onText(R.string.conversations_details_api_error).isNotDisplayed()
    }

    @Test
    fun shouldSendMessageToApiAfterClickOnSendButton() {
        startActivity()
        onId(R.id.sendButton).click()

        verify(api).sendMessage(any(), any())
    }

    @Test
    fun shouldSendCorrectIdToApi() {
        val friendId = "friendId"
        startActivity(friendId)
        onId(R.id.sendButton).click()
        verify(api, times(1)).sendMessage(eq(friendId), any())
    }

    private fun startActivity(friendId: String = "123") {
        rule.launchActivity(ConversationDetailsActivity.startingIntent(InstrumentationRegistry.getTargetContext(), friendId))
    }
}

