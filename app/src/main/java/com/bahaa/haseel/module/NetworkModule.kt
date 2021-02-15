package com.bahaa.haseel.module

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.provider.Settings
import com.bahaa.haseel.application.MyApp
import com.bahaa.haseel.data.retrofitApi.RetrofitApi
import com.bahaa.haseel.screen.splash.SplashActivity

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// App Base Url
const val BASE_URL = ""


val networkModule = module {
    single { getLoggingInterceptor() }
    single { getOkHttp(get(), get(), get()) }
    single { getRetrofit(get()) }
    single { getRetrofitApi(get()) }

}

fun getLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    return httpLoggingInterceptor.apply {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }
}

fun getOkHttp(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    context: Context,
    sharedPreferences: SharedPreferences
): OkHttpClient {
    return OkHttpClient().newBuilder()
        .connectTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()
                .addHeader("Accept-Language", MyApp.language)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Device-Type", "android")
                .addHeader("Device-Name", android.os.Build.MODEL)
                .addHeader("Device-OS-Version", android.os.Build.VERSION.RELEASE)
                .addHeader(
                    "Device-UDID",
                    Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                )
                .addHeader(
                    "Device-Push-Token",
                    sharedPreferences.getString("pushToken", "Not Allowed").toString()
                )
                .addHeader("mobile_version", android.os.Build.VERSION.SDK_INT.toString())

            if (sharedPreferences.contains("token")) {
                builder.addHeader(
                    "Authorization",
                    "Bearer ${sharedPreferences.getString("token", "")}"
                )
            }
            try {
                val pInfo: PackageInfo =
                    context.packageManager.getPackageInfo(context.packageName, 0)
                val version = pInfo.versionName
                builder.addHeader("App-Version", version)
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                builder.addHeader("App-Version", "1")
            }

            val response = chain.proceed(builder.build())

            if (response.code == 401) {
                sharedPreferences.edit().clear().apply()
                val intent = Intent(context, SplashActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }



            response
        }
        .build()
}


fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun getRetrofitApi(retrofit: Retrofit): RetrofitApi {
    return retrofit.create(RetrofitApi::class.java)
}