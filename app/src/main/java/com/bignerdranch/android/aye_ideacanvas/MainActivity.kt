package com.bignerdranch.android.aye_ideacanvas

import android.os.Bundle
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bignerdranch.android.aye_ideacanvas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        val navHomeFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHomeFragment.navController

        binding?.bottomNavigationView?.let { setupWithNavController(it, navController) }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.create) {
                binding?.bottomNavigationView?.visibility = View.GONE
            } else {
                binding?.bottomNavigationView?.visibility = View.VISIBLE
            }
        }
    }

    private fun splashScreen(){
        Thread.sleep(1500)
        installSplashScreen()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}