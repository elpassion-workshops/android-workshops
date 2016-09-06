package com.elpassion.secretmessenger.conversations

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.elpassion.secretmessenger.R

class ConversationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conversations_activity)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ConversationsActivity::class.java))
        }
    }
}