package com.elpassion.secretmessenger.conversation.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.conversation.details.ConversationDetailsActivity
import kotlinx.android.synthetic.main.conversation_add_layout.*

class ConversationAddActivity : AppCompatActivity(), ConversationAdd.View {

    val controller = ConversationAddController(ConversationAdd.ApiProvider.get(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conversation_add_layout)
        usersContainer.layoutManager = LinearLayoutManager(this)
        controller.onCreate()
    }

    override fun openConversation(user: User) {
        ConversationDetailsActivity.start(this, user.id)
    }

    override fun hideLoader() {

    }

    override fun showLoader() {

    }

    override fun showError() {

    }

    override fun showUsersList(listOf: List<User>, onUserListener: OnUserListener) {
        usersContainer.adapter = UsersAdapter(listOf.map { UserItemAdapter(it, onUserListener) })
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ConversationAddActivity::class.java))
        }
    }
}

