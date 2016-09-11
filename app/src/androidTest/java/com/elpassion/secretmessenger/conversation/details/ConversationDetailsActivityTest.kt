package com.elpassion.secretmessenger.conversation.details

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.onId
import com.elpassion.secretmessenger.R
import org.junit.Rule
import org.junit.Test

class ConversationDetailsActivityTest {

    @JvmField @Rule
    val rule = ActivityTestRule<ConversationDetailsActivity>(ConversationDetailsActivity::class.java)

    @Test
    fun shouldShowContainerForMessages() {
        onId(R.id.messagesContainer).isDisplayed()
    }
}

