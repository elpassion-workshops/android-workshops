package com.elpassion.secretmessenger.conversation.list

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.click
import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.onText
import com.elpassion.secretmessenger.common.InitIntentsRule
import com.elpassion.secretmessenger.common.checkIntent
import com.elpassion.secretmessenger.conversation.details.ConversationDetailsActivity
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Rule
import org.junit.Test
import rx.Observable

/**
 * Created by wojciechtopolski on 11/09/16.
 */
class ConversationListActivityTest {

    val api = mock<ConversationList.Api>()

    @JvmField @Rule
    val rule = object : ActivityTestRule<ConversationListActivity>(ConversationListActivity::class.java) {
        override fun beforeActivityLaunched() {
            whenever(api.getUserConversationList())
                    .thenReturn(Observable.just(listOf(Conversation("id1", "conversation1"), Conversation("id2", "conversation2"))))
            ConversationList.ApiProvider.override = { api }
        }
    }

    @JvmField @Rule
    val intentsRule = InitIntentsRule()

    @Test
    fun shouldShowConversationListFromApi() {
        onText("conversation1").isDisplayed()
        onText("conversation2").isDisplayed()
    }

    @Test
    fun shouldStartConversationDetails() {
        onText("conversation1").click()
        checkIntent(ConversationDetailsActivity::class.java)
    }
}