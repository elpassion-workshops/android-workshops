package com.elpassion.secretmessenger.conversation.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View.VISIBLE
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.conversation.add.AddConversationActivity
import kotlinx.android.synthetic.main.conversations_activity.*

class ConversationsActivity : AppCompatActivity(), Conversations.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conversations_activity)
        val controller = ConversationsController(this, ConversationsApiProvider.get())
        controller.onCreate()
        addConversationButton.setOnClickListener { controller.onAddConversation() }
        conversationsContainer.layoutManager = LinearLayoutManager(this)
    }

    override fun showConversationsPlaceholder() {
        noConversationsInfo.visibility = VISIBLE
    }

    override fun showConversations(conversations: List<Conversation>) {
        conversationsContainer.adapter = ConversationsAdapter(conversations.map(::ConversationItemAdapter))
    }

    override fun showError() {
    }

    override fun showLoader() {
    }

    override fun hideLoader() {

    }

    override fun openConversationScreen(conversationUuid: String) {
    }

    override fun openAddConversationScreen() {
        AddConversationActivity.start(this)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ConversationsActivity::class.java))
        }
    }
}
