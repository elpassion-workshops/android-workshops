package com.elpassion.secretmessenger.login.impl

import com.elpassion.secretmessenger.common.Provider
import com.elpassion.secretmessenger.login.Login

object LoginApiProvider : Provider<Login.Api>({ FirebaseLoginApi() })