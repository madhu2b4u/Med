package com.demo.med

import android.content.Intent
import android.os.Bundle
import com.demo.med.auth.LoginActivity
import com.demo.med.common.BaseActivity
import com.demo.med.common.LOGGEDIN
import com.demo.med.home.presentation.ui.activity.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handleSplashPage()
    }

    private fun handleSplashPage() {
        val activityScope = CoroutineScope(Dispatchers.Main)
        activityScope.launch {
            delay(1000)
            val isLoggedIn = sharedPrefsHelpers?.getBoolean(LOGGEDIN, false)
            isLoggedIn?.let {
                navigateToLoginOrHome(it)
            }
        }
    }

    private fun navigateToLoginOrHome(isLoggedIn: Boolean) {
        when (isLoggedIn) {
            true -> navigateToHome()
            else -> navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToHome() {
        val intent = Intent(this@MainActivity, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}