package com.demo.med.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.demo.med.R
import com.demo.med.common.BaseActivity
import com.demo.med.common.LOGGEDIN
import com.demo.med.common.USERNAME
import com.demo.med.common.extensions.hideKeyboard
import com.demo.med.common.extensions.shortToast
import com.demo.med.home.presentation.ui.activity.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.loginButton
import kotlinx.android.synthetic.main.activity_login.password
import kotlinx.android.synthetic.main.activity_login.passwordLayout
import kotlinx.android.synthetic.main.activity_login.username
import kotlinx.android.synthetic.main.activity_login.usernameLayout

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        handleLogin()
    }

    private fun handleLogin() {

        loginButton.setOnClickListener {
            hideKeyboard()
            // Validate inputs
            if (isInputValid()) {
                // Call login() function on the ViewModel
                viewModel.login(username.text.toString(), password.text.toString())
            }
        }

        // Observe the loginSuccess LiveData from the ViewModel
        viewModel.loginSuccess.observe(this) { username ->
            handleLoginResponse(username)
        }
    }

    //handle login success
    private fun handleLoginResponse(username: String) {
        shortToast("Logged in successfully as $username")
        sharedPrefsHelpers?.putString(USERNAME, username)
        sharedPrefsHelpers?.putBoolean(LOGGEDIN, true)
        navigateToHome()
    }

    //navigate to home page after successful login
    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    // Validate input fields
    private fun isInputValid(): Boolean {
        var isValid = true

        // Validate username
        if (username.text.toString().trim().isEmpty()) {
            usernameLayout.error = getString(R.string.username_is_required)
            isValid = false
        } else {
            usernameLayout.error = null
        }

        // Validate password
        if (password.text.toString().trim().isEmpty()) {
            passwordLayout.error = getString(R.string.password_is_required)
            isValid = false
        } else {
            passwordLayout.error = null
        }

        return isValid
    }
}