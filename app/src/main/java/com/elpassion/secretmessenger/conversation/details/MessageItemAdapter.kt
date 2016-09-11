package com.elpassion.secretmessenger.conversation.details

import kotlinx.android.synthetic.main.message_item.view.*

class MessageItemAdapter(private val message: com.elpassion.secretmessenger.conversation.details.Message) : com.elpassion.android.commons.recycler.ItemAdapter<MessageItemAdapter.VH>(com.elpassion.secretmessenger.R.layout.message_item) {

    override fun onBindViewHolder(holder: com.elpassion.secretmessenger.conversation.details.MessageItemAdapter.VH) {
        holder.itemView.messagesTextView.text = message.text
    }

    override fun onCreateViewHolder(itemView: android.view.View) = com.elpassion.secretmessenger.conversation.details.MessageItemAdapter.VH(itemView)

    class VH(itemView: android.view.View) : android.support.v7.widget.RecyclerView.ViewHolder(itemView)
}