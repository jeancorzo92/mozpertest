package com.jeancorzo.rickandmorty.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.jeancorzo.rickandmorty.R
import com.jeancorzo.rickandmorty.home.HomeActivity
import com.jeancorzo.rickandmorty.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.uiState.observe(this) { direction ->
            handleUiState(direction)
        }
    }

    private fun handleUiState(direction: SplashUiState) {
        when (direction) {
            SplashUiState.NavigateToHome -> navigateToHome()
            SplashUiState.NavigateToLogin -> navigateToLogin()
            else -> {}
        }
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}