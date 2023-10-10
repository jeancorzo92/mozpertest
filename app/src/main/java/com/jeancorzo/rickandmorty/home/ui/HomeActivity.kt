package com.jeancorzo.rickandmorty.home.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jeancorzo.rickandmorty.R
import com.jeancorzo.rickandmorty.databinding.ActivityHomeBinding
import com.jeancorzo.rickandmorty.login.ui.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : FragmentActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        setUpNavigation()
        setUpMenu()
        observeViewModel()
    }

    private fun setUpNavigation() {
        navController = findNavController(R.id.home_nav_host_fragment)
    }

    private fun setUpMenu() {
        binding.homeToolbar.setOnMenuItemClickListener {  menuItem ->
            when (menuItem.itemId) {
                R.id.logout -> {
                    viewModel.logoutRequested()
                    true
                }
                else -> false
            }
        }
    }

    private fun observeViewModel() {
        viewModel.navigateToLogin.observe(this) {
            navigateToLogin()
        }

        viewModel.showLogOutConfirmation.observe(this) {
            showLogoutConfirmationDialog()
        }
    }

    private fun showLogoutConfirmationDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.logout_confirmation_title)
            .setPositiveButton(R.string.accept) { _, _ -> viewModel.logOutConfirmed() }
            .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}