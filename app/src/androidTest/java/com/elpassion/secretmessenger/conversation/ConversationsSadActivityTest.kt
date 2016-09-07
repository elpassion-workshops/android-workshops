package com.elpassion.secretmessenger.conversation

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.click
import com.elpassion.android.commons.espresso.hasText
import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.onId
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.common.InitIntentsRule
import com.elpassion.secretmessenger.common.checkIntent
import com.elpassion.secretmessenger.conversation.add.AddConversationActivity
import com.elpassion.secretmessenger.conversation.list.Conversations
import com.elpassion.secretmessenger.conversation.list.ConversationsActivity
import com.elpassion.secretmessenger.conversation.list.ConversationsApiProvider
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Rule
import org.junit.Test
import rx.Observable.just

class ConversationsSadActivityTest {

    val api = mock<Conversations.Api>() {
        on { call() } doReturn just(emptyList())
    }

    @JvmField @Rule
    val rule = object : ActivityTestRule<ConversationsActivity>(ConversationsActivity::class.java) {
        override fun beforeActivityLaunched() {
            ConversationsApiProvider.override = { api }
        }
    }

    @JvmField @Rule
    val intentRule = InitIntentsRule()

    @Test
    fun shouldShowConversationsPlaceholderWhenThereIsNoData() {
        onId(R.id.noConversationsInfo).isDisplayed()
    }

    @Test
    fun shouldHaveAddConversationButton() {
        onId(R.id.addConversationButton).isDisplayed()
    }

    @Test
    fun placeholderShouldHaveCorrectText() {
        onId(R.id.noConversationsInfo).hasText(R.string.no_conversations)
    }

    @Test
    fun shouldOpenAddConversationScreenOnAddConversations() {
        onId(R.id.addConversationButton).click()

        checkIntent(AddConversationActivity::class.java)
    }
}

