package com.elpassion.secretmessenger.conversation.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.elpassion.android.commons.recycler.BaseRecyclerViewAdapter
import com.elpassion.android.commons.recycler.ItemAdapter
import com.elpassion.secretmessenger.R
import kotlinx.android.synthetic.main.conversation_list_item_layout.view.*
import kotlinx.android.synthetic.main.conversation_list_layout.*

class ConversationListActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ConversationListActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conversation_list_layout)

        conversationList.adapter = CustomAdapter()
        conversationList.layoutManager = LinearLayoutManager(this)
    }

    class CustomAdapter : BaseRecyclerViewAdapter(listOf(
            ConversationItemAdapter(Conversation("First conversation")),
            ConversationItemAdapter(Conversation("Second conversation"))
    ))

    class ConversationItemAdapter(val conversation: Conversation) : ItemAdapter<CustomViewHolder>(R.layout.conversation_list_item_layout) {

        override fun onCreateViewHolder(itemView: View) = CustomViewHolder(itemView)

        override fun onBindViewHolder(holder: CustomViewHolder) {
            holder.itemView.textLine.text = conversation.content
        }
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}