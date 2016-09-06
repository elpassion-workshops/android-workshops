package com.elpassion.secretmessenger.conversations

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.isNotDisplayed
import com.elpassion.android.commons.espresso.onId
import com.elpassion.secretmessenger.R
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Rule
import org.junit.Test
import rx.Observable.just

class ConversationsActivityTest {

    val api = mock<Conversations.Api>() {
        on { call() } doReturn just(emptyList())
    }

    @JvmField @Rule
    val rule = object : ActivityTestRule<ConversationsActivity>(ConversationsActivity::class.java, false, false) {
        override fun beforeActivityLaunched() {
            ConversationsApiProvider.override = { api }
        }
    }

    @Test
    fun shouldShowConversationsPlaceholderWhenThereIsNoData() {
        rule.launchActivity(Intent())
        onId(R.id.noConversationsInfo).isDisplayed()
    }

    @Test
    fun shouldNotShowConversationsPlaceholderIfApiReturnsData() {
        stubApiToReturnConversations(listOf(Conversation()))
        rule.launchActivity(Intent())
        onId(R.id.noConversationsInfo).isNotDisplayed()
    }

    private fun stubApiToReturnConversations(conversations: List<Conversation>) {
        whenever(api.call()).thenReturn(just(conversations))
    }
}