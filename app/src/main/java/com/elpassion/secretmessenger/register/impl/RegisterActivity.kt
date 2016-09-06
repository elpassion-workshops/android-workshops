package com.elpassion.secretmessenger.register.impl

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, RegisterActivity::class.java))
        }
    }

}