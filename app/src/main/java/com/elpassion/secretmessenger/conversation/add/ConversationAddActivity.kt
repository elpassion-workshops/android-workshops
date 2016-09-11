package com.elpassion.secretmessenger.conversation.add

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.elpassion.secretmessenger.R
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

    }

    override fun hideLoader() {

    }

    override fun showLoader() {

    }

    override fun showError() {

    }

    override fun showUsersList(listOf: List<User>) {
        usersContainer.adapter = UsersAdapter(listOf.map { UserItemAdapter(it) })
    }
}

