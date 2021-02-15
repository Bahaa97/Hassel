package com.bahaa.haseel.screen.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.bahaa.haseel.R
import com.bahaa.haseel.base.BaseActivity
import com.bahaa.haseel.screen.checkOut.CheckOutViewModelImp
import com.bahaa.haseel.databinding.ActivitySplashBinding
import com.bahaa.haseel.screen.checkOut.CheckOutActivity
import kotlin.reflect.KClass

// Used checkout view model because there is no need to use a view model for splash and no logic need to handle
class SplashActivity : BaseActivity<ActivitySplashBinding, CheckOutViewModelImp>() {

    override fun resourceId(): Int = R.layout.activity_splash

    override fun viewModelClass(): KClass<CheckOutViewModelImp> = CheckOutViewModelImp::class
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            startActivity(Intent(this, CheckOutActivity::class.java))
            overridePendingTransition(R.anim.slide_up, R.anim.no_change)
            finish()
        }, 1100)

    }

}