package com.demo.med

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.demo.med.common.SpUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MedApp : Application() {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate() {
        super.onCreate()
        SpUtil.instance?.init(this)
    }
}