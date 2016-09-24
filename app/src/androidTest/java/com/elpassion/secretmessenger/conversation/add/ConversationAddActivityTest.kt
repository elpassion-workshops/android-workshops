package com.elpassion.secretmessenger.conversation.add

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.click
import com.elpassion.android.commons.espresso.onId
import com.elpassion.android.commons.espresso.onText
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.common.InitIntentsRule
import com.elpassion.secretmessenger.common.checkIntent
import com.elpassion.secretmessenger.common.hasChildWithText
import com.elpassion.secretmessenger.conversation.details.ConversationDetails
import com.elpassion.secretmessenger.conversation.details.ConversationDetailsActivity
import com.elpassion.secretmessenger.conversation.details.Message
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Rule
import org.junit.Test
import rx.Observable

class ConversationAddActivityTest {
    val api = mock<ConversationAdd.Api>()
    val conversationApi = mock<ConversationDetails.Api>() {
        on { getMessages(any()) } doReturn Observable.just(Message("message"))
    }

    @JvmField @Rule
    val rule = object : ActivityTestRule<ConversationAddActivity>(ConversationAddActivity::class.java) {
        override fun beforeActivityLaunched() {
            whenever(api.fetchUsers()).thenReturn(Observable.just(listOf(User("Kasper", ""))))
            ConversationAdd.ApiProvider.override = { api }
            ConversationDetails.ApiProvider.override = { conversationApi }
        }
    }

    @JvmField @Rule
    val intentsRule = InitIntentsRule()

    @Test
    fun shouldDisplayReturnedUserOnList() {
        onId(R.id.usersContainer).hasChildWithText("Kasper")
    }

    @Test
    fun shouldOpenConversationOnUser() {
        onText("Kasper").click()
        checkIntent(ConversationDetailsActivity::class.java)
    }
}

