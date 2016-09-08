package com.elpassion.secretmessenger.conversation.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.elpassion.android.commons.recycler.BaseRecyclerViewAdapter
import com.elpassion.android.commons.recycler.ItemAdapter
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.conversation.details.ConversationDetailsActivity
import kotlinx.android.synthetic.main.add_conversation_activity.*

class AddConversationActivity : AppCompatActivity(), AddConversation.View {

    val controller by lazy { AddConversationController(this, AddConversation.UsersApiProvider.get(), AddConversation.AddApiProvider.get()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_conversation_activity)
        usersContainer.layoutManager = LinearLayoutManager(this)
        controller.onCreate()
    }

    override fun showLoader() {
    }

    override fun hideLoader() {
    }

    override fun showUsers(users: List<String>, onAddConversationListener: OnAddConversationListener) {
        usersContainer.adapter = UsersAdapter(users.map { UserItemAdapter(it, onAddConversationListener) })
    }

    override fun openConversationDetails(otherPersonEmail: String) {
        ConversationDetailsActivity.start(this, otherPersonEmail)
    }

    override fun showError() {
        throw UnsupportedOperationException("not implemented")
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, AddConversationActivity::class.java))
        }
    }
}

class UserItemAdapter(val userEmail: String, val onAddConversationListener: OnAddConversationListener) : ItemAdapter<UserItemAdapter.VH>(R.layout.user_item) {

    override fun onCreateViewHolder(itemView: View): VH = VH(itemView)

    override fun onBindViewHolder(holder: VH) {
        (holder.itemView as TextView).run {
            text = userEmail
            setOnClickListener { onAddConversationListener.onAddConversation(userEmail) }
        }
    }

    class VH(view: View) : RecyclerView.ViewHolder(view)

}

class UsersAdapter(userItems: List<ItemAdapter<*>>) : BaseRecyclerViewAdapter(userItems)