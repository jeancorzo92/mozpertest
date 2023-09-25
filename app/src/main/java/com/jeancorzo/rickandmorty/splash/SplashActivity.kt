package com.jeancorzo.rickandmorty.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jeancorzo.rickandmorty.R
import com.jeancorzo.rickandmorty.home.HomeActivity
import com.jeancorzo.rickandmorty.login.LoginActivity
import com.jeancorzo.rickandmorty.session.LocalSessionManager
import com.jeancorzo.rickandmorty.storage.PreferenceDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    //TODO di
    private val viewModel by lazy { SplashViewModel(LocalSessionManager(PreferenceDataStore(this))) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.navigateToDirection.observe(this) { direction ->
            when(direction) {
                SplashNavigationDirection.ToHome -> navigateToHome()
                SplashNavigationDirection.ToLogin -> navigateToLogin()
            }
        }
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}