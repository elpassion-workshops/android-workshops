package com.elpassion.secretmessenger.conversation.details

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity

class ConversationDetailsActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ConversationDetailsActivity::class.java))
        }
    }
}