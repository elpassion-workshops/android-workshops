package com.elpassion.secretmessenger.conversation

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.isNotDisplayed
import com.elpassion.android.commons.espresso.onId
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.common.hasChildWithText
import com.elpassion.secretmessenger.conversation.list.Conversation
import com.elpassion.secretmessenger.conversation.list.Conversations
import com.elpassion.secretmessenger.conversation.list.ConversationsActivity
import com.elpassion.secretmessenger.conversation.list.ConversationsApiProvider
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Rule
import org.junit.Test
import rx.Observable.just

class ConversationsHappyActivityTest {

    val conversation = Conversation(id = "1", otherPersonName = "Kasper")

    val api = mock<Conversations.Api>() {
        on { call() } doReturn just(listOf(conversation))
    }

    @JvmField @Rule
    val rule = object : ActivityTestRule<ConversationsActivity>(ConversationsActivity::class.java) {
        override fun beforeActivityLaunched() {
            ConversationsApiProvider.override = { api }
        }
    }

    @Test
    fun shouldNotShowConversationsPlaceholderIfApiReturnsData() {
        onId(R.id.noConversationsInfo).isNotDisplayed()
    }

    @Test
    fun shouldShowConversationsFromApi() {
        onId(R.id.conversationsContainer).hasChildWithText(conversation.otherPersonName)
    }
}