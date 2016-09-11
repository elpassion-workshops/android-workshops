package com.elpassion.secretmessenger.conversation.list

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.onText
import org.junit.Rule
import org.junit.Test

/**
 * Created by wojciechtopolski on 11/09/16.
 */
class ConversationListActivityTest {

    @JvmField @Rule
    val rule = object : ActivityTestRule<ConversationListActivity>(ConversationListActivity::class.java) {

    }

    @Test
    fun shouldShowConversationList() {
        onText("First conversation").isDisplayed()
        onText("Second conversation").isDisplayed()
    }
}