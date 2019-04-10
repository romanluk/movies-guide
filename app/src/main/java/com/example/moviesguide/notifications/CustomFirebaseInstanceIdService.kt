package com.example.moviesguide.notifications

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class CustomFirebaseInstanceIdService : FirebaseInstanceIdService() {
    private val tag = "PushService"
    lateinit var name: String

    override fun onTokenRefresh() {
        val token = FirebaseInstanceId.getInstance().token
        Log.d(tag, "Token refreshed: $token")
    }
}