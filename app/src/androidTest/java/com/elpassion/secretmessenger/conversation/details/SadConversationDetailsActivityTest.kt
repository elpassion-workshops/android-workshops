package com.elpassion.secretmessenger.conversation.details

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.onText
import com.elpassion.secretmessenger.R
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Rule
import org.junit.Test
import rx.Observable

class SadConversationDetailsActivityTest {

    val api = mock<ConversationDetails.Api>() {
        on { getMessages(any()) } doReturn Observable.error(RuntimeException())
    }

    @JvmField @Rule
    val rule = object : ActivityTestRule<ConversationDetailsActivity>(ConversationDetailsActivity::class.java, false, false) {
        override fun beforeActivityLaunched() {
            ConversationDetails.ApiProvider.override = { api }
        }
    }

    @Test
    fun shouldShowErrorMessageIfApiFails() {
        startActivity()
        onText(R.string.conversations_details_api_error).isDisplayed()
    }

    private fun startActivity(friendId: String = "123") {
        rule.launchActivity(ConversationDetailsActivity.startingIntent(InstrumentationRegistry.getTargetContext(), friendId))
    }
}