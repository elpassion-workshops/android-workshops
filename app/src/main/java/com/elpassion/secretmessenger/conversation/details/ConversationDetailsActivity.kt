package com.elpassion.secretmessenger.conversation.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.elpassion.android.commons.recycler.BaseRecyclerViewAdapter
import com.elpassion.android.commons.recycler.ItemAdapter
import com.elpassion.secretmessenger.R
import kotlinx.android.synthetic.main.conversation_details_layout.*
import kotlinx.android.synthetic.main.message_item.view.*

class ConversationDetailsActivity : AppCompatActivity(), ConversationDetails.View {

    private val adapter = ConversationDetailsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conversation_details_layout)
        val controller = ConversationDetailsController(this, ConversationDetails.ApiProvider.get())
        controller.onCreate()

        messagesContainer.layoutManager = LinearLayoutManager(this)
        messagesContainer.adapter = adapter
    }

    override fun showMessages(messages: List<Message>) {
        adapter.setMessages(messages.map(::MessageItemAdapter))
    }

    override fun showError() {
    }

    class ConversationDetailsAdapter() : BaseRecyclerViewAdapter() {

        fun setMessages(messages: List<MessageItemAdapter>) {
            adapters.clear()
            adapters.addAll(messages)
            notifyDataSetChanged()
        }
    }

    class MessageItemAdapter(private val message: Message) : ItemAdapter<MessageItemAdapter.VH>(R.layout.message_item) {

        override fun onBindViewHolder(holder: VH) {
            holder.itemView.messagesTextView.text = message.text
        }

        override fun onCreateViewHolder(itemView: View) = VH(itemView)

        class VH(itemView: View) : RecyclerView.ViewHolder(itemView)
    }
}