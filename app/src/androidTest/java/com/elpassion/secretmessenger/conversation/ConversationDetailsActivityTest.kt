package com.elpassion.secretmessenger.conversation

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import com.elpassion.secretmessenger.conversation.details.ConversationDetailsActivity
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Rule
import org.junit.Test

class ConversationDetailsActivityTest {

    val conversationApi = mock<ConversationDetails.Api>()

    @JvmField @Rule
    val rule = object : ActivityTestRule<ConversationDetailsActivity>(ConversationDetailsActivity::class.java) {
        override fun beforeActivityLaunched() {
            ConversationDetails.ApiProvider.override = { conversationApi }
        }

        override fun getActivityIntent() = ConversationDetailsActivity.intent(InstrumentationRegistry.getTargetContext(), "123")
    }

    @Test
    fun shouldCallConversationDetailsApiForGivenConversationId() {
        verify(conversationApi).getConversation("123")
    }
}

