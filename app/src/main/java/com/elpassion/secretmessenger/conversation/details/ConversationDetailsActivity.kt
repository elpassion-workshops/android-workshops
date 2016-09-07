package com.elpassion.secretmessenger.conversation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.elpassion.secretmessenger.conversation.ConversationDetails

class ConversationDetailsActivity : AppCompatActivity() {
    companion object {

        private val uuidKey = "uuidKey"

        fun start(context: Context, conversationUuid: String) {
            context.startActivity(intent(context, conversationUuid))
        }

        fun intent(context: Context, conversationUuid: String) = Intent(context, ConversationDetailsActivity::class.java).apply {
            putExtra(uuidKey, conversationUuid)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ConversationDetails.ApiProvider.get().getConversation(intent.getStringExtra(uuidKey))
    }
}