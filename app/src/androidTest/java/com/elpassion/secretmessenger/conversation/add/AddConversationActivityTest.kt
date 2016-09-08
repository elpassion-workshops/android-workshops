package com.elpassion.secretmessenger.conversation.add

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.click
import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.onId
import com.elpassion.android.commons.espresso.onText
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.common.InitIntentsRule
import com.elpassion.secretmessenger.common.checkIntent
import com.elpassion.secretmessenger.common.hasChildWithText
import com.elpassion.secretmessenger.conversation.details.ConversationDetailsActivity
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Rule
import org.junit.Test
import rx.Observable

class AddConversationActivityTest {

    val usersApi = mock<AddConversation.UsersApi>() {
        on { this.getUsers() } doReturn Observable.just(listOf("User 1", "User 2"))
    }
    val addApi = mock<AddConversation.AddApi>()

    @JvmField @Rule
    val rule = object : ActivityTestRule<AddConversationActivity>(AddConversationActivity::class.java) {
        override fun beforeActivityLaunched() {
            AddConversation.AddApiProvider.override = { addApi }
            AddConversation.UsersApiProvider.override = { usersApi }
        }
    }

    @JvmField @Rule
    val intentRule = InitIntentsRule()

    @Test
    fun shouldShowListOfUsersFromApi() {
        onId(R.id.usersContainer).hasChildWithText("User 1")
        onId(R.id.usersContainer).hasChildWithText("User 2")
    }

    @Test
    fun shouldOpenConversationDetailsOnAddClicked() {
        whenever(addApi.addConversation(any())).thenReturn(Observable.just(""))
        onText("User 1").click()
        checkIntent(ConversationDetailsActivity::class.java)
    }
}

