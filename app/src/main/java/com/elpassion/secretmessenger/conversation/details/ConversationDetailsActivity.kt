package com.elpassion.secretmessenger.conversation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class ConversationDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ConversationDetails.ApiProvider.get().getConversation(intent.getStringExtra(otherPersonEmailKey))
    }

    companion object {
        private val otherPersonEmailKey = "otherPersonEmailKey"

        fun start(context: Context, otherPersonEmail: String) {
            context.startActivity(intent(context, otherPersonEmail))
        }

        fun intent(context: Context, otherPersonEmail: String) = Intent(context, ConversationDetailsActivity::class.java).apply {
            putExtra(otherPersonEmailKey, otherPersonEmail)
        }
    }
}