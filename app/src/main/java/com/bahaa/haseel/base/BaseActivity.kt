package com.bahaa.haseel.base

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.bahaa.haseel.R
import com.bahaa.haseel.application.MyApp
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.getViewModel
import java.util.*
import kotlin.reflect.KClass


abstract class BaseActivity<T : ViewDataBinding, V : ViewModel> : AppCompatActivity() {

    lateinit var dataBinding: T

    val viewModel: V by lazy { getViewModel(viewModelClass()) }

    var dialog: AlertDialog? = null


    val sharedPreferences: SharedPreferences by inject()

    abstract fun resourceId(): Int

    abstract fun viewModelClass(): KClass<V>

    override fun onCreate(savedInstanceState: Bundle?) {
        changeLang()
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, resourceId())
        dataBinding.lifecycleOwner = this

        window.decorView.layoutDirection = if (MyApp.language == "ar") {
            View.LAYOUT_DIRECTION_RTL
        } else {
            View.LAYOUT_DIRECTION_LTR
        }

        toggleLoadingDialog(false)


        changeLang()
    }

    override fun onPause() {
        super.onPause()
        dialog?.dismiss()
    }

    override fun onStop() {
        super.onStop()
        dialog?.dismiss()
    }


    override fun onStart() {
        super.onStart()
        changeLang()
    }

    fun changeLang() {
        val res: Resources = resources
        val dm: DisplayMetrics = res.displayMetrics
        val conf: Configuration = res.configuration
        val locale = Locale(MyApp.language)
        if (conf.locale != locale) {
            changeLocale(this, MyApp.language)
            conf.setLocale(Locale(MyApp.language))
            res.updateConfiguration(conf, dm)
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale
            applicationContext.resources
                .updateConfiguration(config, applicationContext.resources.displayMetrics)
        }

    }




    open fun changeLocale(context: Context, locale: String?) {
        val res: Resources = context.resources
        val conf: Configuration = res.configuration
        conf.locale = Locale(locale)
        res.updateConfiguration(conf, res.displayMetrics)


    }

    fun toggleLoadingDialog(show: Boolean) {

        if (dialog == null) {
            dialog = AlertDialog.Builder(this)
                .setView(R.layout.progress)
                .setCancelable(false)
                .create()
            dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        }

        if (!show)
            dialog?.dismiss()
        else if (show)
            dialog?.show()


    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.no_change, R.anim.slide_down)
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog?.dismiss()
    }

    fun isLogin(): Boolean {
        return !sharedPreferences.getString("token", "").equals("")
    }





}