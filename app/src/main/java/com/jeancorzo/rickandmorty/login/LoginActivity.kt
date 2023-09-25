package com.jeancorzo.rickandmorty.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import com.jeancorzo.rickandmorty.R
import com.jeancorzo.rickandmorty.databinding.ActivityLoginBinding
import com.jeancorzo.rickandmorty.home.HomeActivity
import com.jeancorzo.rickandmorty.utils.gone
import com.jeancorzo.rickandmorty.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.uiState.observe(this) { state ->
            renderUiState(state)
        }
    }

    private fun renderUiState(state: LoginUiState) {
        when(state) {
            LoginUiState.Loading -> showLoading()
            LoginUiState.LoginSuccess -> navigateToHome()
            LoginUiState.WaitingUserInput -> hideLoading()
        }
    }

    private fun showLoading() {
        binding.loginButton.gone()
        binding.loginProgressBar.visible()
    }

    private fun hideLoading() {
        binding.loginButton.visible()
        binding.loginProgressBar.gone()
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
    }
}