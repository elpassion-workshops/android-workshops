package com.elpassion.secretmessenger.conversation.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.elpassion.android.view.show
import com.elpassion.secretmessenger.R
import kotlinx.android.synthetic.main.conversation_details_layout.*

class ConversationDetailsActivity : AppCompatActivity(), ConversationDetails.View {

    private val adapter = ConversationDetailsAdapter()
    private val controller = ConversationDetailsController(this, ConversationDetails.ApiProvider.get())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conversation_details_layout)
        controller.onCreate()
    }

    override fun init() {
        messagesContainer.layoutManager = LinearLayoutManager(this)
        messagesContainer.adapter = adapter
        sendButton.setOnClickListener {
            controller.onMessageSend("")
        }
    }

    override fun showMessages(messages: List<Message>) {
        adapter.setMessages(messages.map(::MessageItemAdapter))
    }

    override fun showError() {
        errorMessage.show()
    }
}