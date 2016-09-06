package com.elpassion.secretmessenger.login.impl

import com.elpassion.secretmessenger.common.Provider
import com.elpassion.secretmessenger.login.LoginApi

object LoginApiProvider : Provider<LoginApi>({ FirebaseLoginApi() })