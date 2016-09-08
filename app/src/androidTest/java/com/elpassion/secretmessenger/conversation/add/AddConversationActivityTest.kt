package com.elpassion.secretmessenger.conversation.add

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.click
import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.onId
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.common.InitIntentsRule
import com.elpassion.secretmessenger.common.checkIntent
import com.elpassion.secretmessenger.conversation.details.ConversationDetailsActivity
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Rule
import org.junit.Test
import rx.Observable

class AddConversationActivityTest {

    val api = mock<AddConversation.AddApi>()

    @JvmField @Rule
    val rule = object : ActivityTestRule<AddConversationActivity>(AddConversationActivity::class.java) {
        override fun beforeActivityLaunched() {
            AddConversation.ApiProvider.override = { api }
        }
    }

    @JvmField @Rule
    val intentRule = InitIntentsRule()

    @Test
    fun shouldShowAddConversationButton() {
        onId(R.id.addConversationButton).isDisplayed()
    }

    @Test
    fun shouldOpenConversationDetailsOnAddClicked() {
        whenever(api.addConversation()).thenReturn(Observable.just(""))
        onId(R.id.addConversationButton).click()
        checkIntent(ConversationDetailsActivity::class.java)
    }
}

