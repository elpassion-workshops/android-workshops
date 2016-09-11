package com.elpassion.secretmessenger.conversation.add

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.onId
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.common.hasChildWithText
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Rule
import org.junit.Test
import rx.Observable

class ConversationAddActivityTest {
    val api = mock<ConversationAdd.Api>()

    @JvmField @Rule
    val rule = object : ActivityTestRule<ConversationAddActivity>(ConversationAddActivity::class.java) {
        override fun beforeActivityLaunched() {
            whenever(api.fetchUsers()).thenReturn(Observable.just(listOf(User("Kasper"))))
            ConversationAdd.ApiProvider.override = { api }
        }
    }

    @Test
    fun shouldDisplayReturnedUserOnList() {
        onId(R.id.usersContainer).hasChildWithText("Kasper")
    }


}

