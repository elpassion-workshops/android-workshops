package com.elpassion.secretmessenger.register

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.elpassion.secretmessenger.R

class RegisterActivity : AppCompatActivity(), Register.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)
    }

    override fun showErrorPasswordsDontMatch() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorEmptyPassword() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorEmptyLogin() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorRegistrationFail() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}