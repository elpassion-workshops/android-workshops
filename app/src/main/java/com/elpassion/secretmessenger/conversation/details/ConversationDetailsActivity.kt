package com.elpassion.secretmessenger.conversation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.conversation.add.ConversationAddActivity
import kotlinx.android.synthetic.main.conversation_details_layout.*

class ConversationDetailsActivity : AppCompatActivity(), ConversationDetails.View {

    private val adapter = ConversationDetailsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conversation_details_layout)
        val controller = ConversationDetailsController(this, ConversationDetails.ApiProvider.get())
        controller.onCreate()
    }

    override fun init() {
        messagesContainer.layoutManager = LinearLayoutManager(this)
        messagesContainer.adapter = adapter
    }

    override fun showMessages(messages: List<Message>) {
        adapter.setMessages(messages.map(::MessageItemAdapter))
    }

    override fun showError() {
        findViewById(R.id.errorMessage).visibility = View.VISIBLE
    }

    companion object {
        private val userKey = "userId"
        fun start(context: Context, id: String) {
            val intent = Intent(context, ConversationDetailsActivity::class.java)
            intent.putExtra(userKey, id)
            context.startActivity(intent)
        }
    }
}