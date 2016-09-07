package com.elpassion.secretmessenger.conversations

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity

class AddConversationActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, AddConversationActivity::class.java))
        }
    }
}