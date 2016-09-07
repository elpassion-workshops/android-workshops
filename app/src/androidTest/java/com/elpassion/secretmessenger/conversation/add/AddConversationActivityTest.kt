package com.elpassion.secretmessenger.conversation.add

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.onId
import com.elpassion.secretmessenger.R
import org.junit.Rule
import org.junit.Test

class AddConversationActivityTest {

    @JvmField @Rule
    val rule = object : ActivityTestRule<AddConversationActivity>(AddConversationActivity::class.java){}

    @Test
    fun shouldShowAddConversationButton() {
        onId(R.id.addConversationButton).isDisplayed()
    }
}
