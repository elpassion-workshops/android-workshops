package com.elpassion.secretmessenger.conversations

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.VISIBLE
import com.elpassion.secretmessenger.R
import kotlinx.android.synthetic.main.conversations_activity.*

class ConversationsActivity : AppCompatActivity(), Conversations.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conversations_activity)
        ConversationsController(this, ConversationsApiProvider.get()).onCreate()
    }

    override fun showConversationsPlaceholder() {
        noConversationsInfo.visibility = VISIBLE
    }

    override fun showConversations(listOf: List<Conversation>) {
    }

    override fun showError() {
    }

    override fun showLoader() {
    }

    override fun hideLoader() {

    }

    override fun openConversation(conversationUuid: String) {
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ConversationsActivity::class.java))
        }
    }
}