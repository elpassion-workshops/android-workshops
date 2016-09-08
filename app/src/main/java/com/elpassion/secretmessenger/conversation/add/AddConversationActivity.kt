package com.elpassion.secretmessenger.conversation.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.elpassion.secretmessenger.R
import com.elpassion.secretmessenger.conversation.details.ConversationDetailsActivity
import kotlinx.android.synthetic.main.add_conversation_activity.*

class AddConversationActivity : AppCompatActivity(), AddConversation.View {
    override fun hideLoader() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoader() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, AddConversationActivity::class.java))
        }
    }
    val controller = AddConversationController(this, AddConversation.UsersApiProvider.get(), AddConversation.ApiProvider.get())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_conversation_activity)
        addConversationButton.setOnClickListener {
            controller.onAddConversation("email@pl.pl")
        }
    }

    override fun showUsers(users: List<String>) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun openConversationDetails(otherPersonEmail: String) {
        ConversationDetailsActivity.start(this, otherPersonEmail)
    }

    override fun showError() {
        throw UnsupportedOperationException("not implemented")
    }
}