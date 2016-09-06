package com.elpassion.secretmessenger.conversations

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.onId
import com.elpassion.secretmessenger.R
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

    @Test
    fun shouldShowConversationsPlaceholderWhenThereIsNoData() {
        onId(R.id.noConversationsInfo).isDisplayed()
    }
}