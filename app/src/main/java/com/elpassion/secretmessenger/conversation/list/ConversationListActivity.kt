package com.elpassion.secretmessenger.conversation.list

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity

class ConversationListActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ConversationListActivity::class.java))
        }
    }
}