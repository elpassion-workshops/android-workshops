package com.elpassion.secretmessenger.conversation.details

import com.elpassion.android.commons.recycler.BaseRecyclerViewAdapter

class ConversationDetailsAdapter() : BaseRecyclerViewAdapter() {

    fun setMessages(messages: List<MessageItemAdapter>) {
        adapters.clear()
        adapters.addAll(messages)
        notifyDataSetChanged()
    }
}