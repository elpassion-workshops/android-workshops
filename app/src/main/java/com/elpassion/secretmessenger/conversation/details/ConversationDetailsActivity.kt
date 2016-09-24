package com.elpassion.secretmessenger.conversation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.elpassion.android.view.show
import com.elpassion.secretmessenger.R
import kotlinx.android.synthetic.main.conversation_details_layout.*
import java.util.*

class ConversationDetailsActivity : AppCompatActivity(), ConversationDetails.View {

    private val adapter = ConversationDetailsAdapter()
    private val controller by lazy {
        ConversationDetailsController(
                view = this,
                api = ConversationDetails.ApiProvider.get(),
                friendId = intent.getStringExtra(FRIEND_KEY)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conversation_details_layout)
        controller.onCreate()
    }

    override fun init() {
        messagesContainer.layoutManager = LinearLayoutManager(this)
        messagesContainer.adapter = adapter
        sendButton.setOnClickListener {
            controller.onMessageSend("MESSAGE: "+ UUID.randomUUID())
        }
    }

    override fun showMessages(messages: List<Message>) {
        adapter.setMessages(messages.map(::MessageItemAdapter))
    }

    override fun showError() {
        errorMessage.show()
    }

    companion object {

        private val FRIEND_KEY = "friend_id"

        fun start(context: Context, friendId: String) {
            val intent = startingIntent(context, friendId)
            context.startActivity(intent)
        }

        fun startingIntent(context: Context, friendId: String): Intent {
            val intent = Intent(context, ConversationDetailsActivity::class.java)
            intent.putExtra(FRIEND_KEY, friendId)
            return intent
        }
    }
}