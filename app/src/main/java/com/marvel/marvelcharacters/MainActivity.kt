package com.marvel.marvelcharacters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.marvel.marvelcharacters.databinding.ActivityMainBinding
import com.marvel.marvelcharacters.ui.dialogs.LoaderDialogFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        binding.navView.setupWithNavController(navController)
        binding.navView.setOnNavigationItemReselectedListener {}
    }

    fun showLoader(){
        LoaderDialogFragment.newInstance().show(
            supportFragmentManager, LoaderDialogFragment::class.java.simpleName
        )
    }

    fun hideLoader(){
        (supportFragmentManager.findFragmentByTag(LoaderDialogFragment::class.java.simpleName)
                as? LoaderDialogFragment)?.dismiss()
    }
}