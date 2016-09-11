package com.elpassion.secretmessenger.conversation.details

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.onId
import com.elpassion.android.commons.espresso.onText
import com.elpassion.secretmessenger.R
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Rule
import org.junit.Test
import rx.Observable

class ConversationDetailsActivityTest {

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
    fun shouldShowErrorMessageIfApiFails() {
        onText(R.string.conversations_details_api_error).isDisplayed()
    }
}

