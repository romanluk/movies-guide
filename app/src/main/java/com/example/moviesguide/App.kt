package com.example.moviesguide

import android.app.Application
import android.content.Context

class App : Application() {
    var mContext : Context? = null

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }

    public fun getContext(): Context? {
        return mContext
    }
}