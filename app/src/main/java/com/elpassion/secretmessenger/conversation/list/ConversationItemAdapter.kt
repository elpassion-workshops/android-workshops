package com.elpassion.secretmessenger.conversation.list

import android.support.v7.widget.RecyclerView
import android.view.View
import com.elpassion.android.commons.recycler.ItemAdapter
import com.elpassion.secretmessenger.R
import kotlinx.android.synthetic.main.conversation_item.view.*

class ConversationItemAdapter(val conversation: Conversation, val onClickListener: OnConversationClickListener)
: ItemAdapter<ConversationItemAdapter.VH>(R.layout.conversation_item) {

    override fun onCreateViewHolder(itemView: View) = VH(itemView)

    override fun onBindViewHolder(holder: VH) {
        holder.itemView.conversation.text = conversation.otherPersonEmail
        holder.itemView.setOnClickListener { onClickListener.onConversation(conversation.otherPersonEmail) }
    }

    class VH(view: View) : RecyclerView.ViewHolder(view)
}
