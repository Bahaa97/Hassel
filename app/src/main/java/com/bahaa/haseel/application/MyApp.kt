package com.bahaa.haseel.application

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.bahaa.haseel.module.classesModule
import com.bahaa.haseel.module.networkModule
import com.bahaa.haseel.module.preferencesModule
import com.bahaa.haseel.module.viewModelModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {
    companion object {
        var language: String = "ar"
    }

    val sharedPreferences: SharedPreferences by inject()


    override fun onCreate() {
        super.onCreate()

        
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(preferencesModule, viewModelModule, classesModule,networkModule)
        }

        language = if (sharedPreferences.getString("lang","ar") == "en") {
            "en"
        } else {
            "ar"
        }



    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }


}