package com.elpassion.secretmessenger.conversation.add

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.elpassion.android.commons.recycler.ItemAdapter
import com.elpassion.secretmessenger.R


class UserItemAdapter(val user: User, val onUserListener: OnUserListener) :ItemAdapter<UserItemAdapter.VH>(R.layout.user_item) {
    override fun onCreateViewHolder(itemView: View) = VH(itemView)

    override fun onBindViewHolder(holder: VH) {
        (holder.itemView as TextView).run {
            text = user.email
            setOnClickListener { onUserListener.onUser(user) }
        }
    }
    class VH (view: View) : RecyclerView.ViewHolder(view)
}