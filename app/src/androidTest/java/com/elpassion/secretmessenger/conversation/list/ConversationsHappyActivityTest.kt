package com.elpassion.secretmessenger.conversation.list

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.click
import com.elpassion.android.commons.espresso.isNotDisplayed
import com.elpassion.android.commons.espresso.onId
import com.elpassion.android.commons.espresso.onText
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.common.InitIntentsRule
import com.elpassion.secretmessenger.common.checkIntent
import com.elpassion.secretmessenger.common.hasChildWithText
import com.elpassion.secretmessenger.conversation.details.ConversationDetails
import com.elpassion.secretmessenger.conversation.details.ConversationDetailsActivity
import com.elpassion.secretmessenger.conversation.list.Conversation
import com.elpassion.secretmessenger.conversation.list.Conversations
import com.elpassion.secretmessenger.conversation.list.ConversationsActivity
import com.elpassion.secretmessenger.conversation.list.ConversationsApiProvider
import com.nhaarman.mockito_kotlin.*
import org.junit.Rule
import org.junit.Test
import rx.Observable.just

class ConversationsHappyActivityTest {

    val conversation = Conversation(id = "1", otherPersonName = "Kasper")

    val conversationsApi = mock<Conversations.Api>() {
        on { call() } doReturn just(listOf(conversation))
    }

    val singleConversationApi = mock<ConversationDetails.Api>()

    @JvmField @Rule
    val rule = object : ActivityTestRule<ConversationsActivity>(ConversationsActivity::class.java) {
        override fun beforeActivityLaunched() {
            ConversationsApiProvider.override = { conversationsApi }
            ConversationDetails.ApiProvider.override = { singleConversationApi }
        }
    }

    @JvmField @Rule
    val intentRule = InitIntentsRule()

    @Test
    fun shouldNotShowConversationsPlaceholderIfApiReturnsData() {
        onId(R.id.noConversationsInfo).isNotDisplayed()
    }

    @Test
    fun shouldShowConversationsFromApi() {
        onId(R.id.conversationsContainer).hasChildWithText(conversation.otherPersonName)
    }

    @Test
    fun shouldOpenConversationDetailsActivityAfterClickOnConversation() {
        onText(conversation.otherPersonName).click()
        checkIntent(ConversationDetailsActivity::class.java)
    }

    @Test
    fun shouldMakeCallForCorrectConversationWhenConversationDetailsScreenIsOpened() {
        onText(conversation.otherPersonName).click()
        verify(singleConversationApi).getConversation("1")
    }
}

