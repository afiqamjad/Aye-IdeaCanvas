package com.bignerdranch.android.aye_ideacanvas

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.bignerdranch.android.aye_ideacanvas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null

    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreen()

        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val lightItemTextColorStateList = createColorStateList(
            R.color.gray, R.color.black, R.color.black
        )

        val darkItemTextColorStateList = createColorStateList(
            R.color.gray, R.color.white, R.color.white
        )

        val navHomeFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHomeFragment.navController

        var itemTextColorStateList : ColorStateList

        setupWithNavController(binding.bottomNavigationView, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.create || destination.id == R.id.createDetails) {
                binding.bottomNavigationView.visibility = View.GONE

            } else {
                binding.bottomNavigationView.visibility = View.VISIBLE
                if (destination.id == R.id.home) {
                    itemTextColorStateList = darkItemTextColorStateList
                    binding.bottomNavigationView.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
                } else {
                    itemTextColorStateList = lightItemTextColorStateList
                    binding.bottomNavigationView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                }
                binding.bottomNavigationView.itemTextColor = itemTextColorStateList
                binding.bottomNavigationView.itemIconTintList = itemTextColorStateList
            }
        }
    }

    private fun splashScreen(){
        Thread.sleep(1500)
        installSplashScreen()
    }

    private fun createColorStateList(defaultColorResId: Int, pressedColorResId: Int, checkedColorResId: Int): ColorStateList {
        val defaultColor = ContextCompat.getColor(this, defaultColorResId)
        val pressedColor = ContextCompat.getColor(this, pressedColorResId)
        val checkedColor = ContextCompat.getColor(this, checkedColorResId)

        val states = arrayOf(
            intArrayOf(android.R.attr.state_pressed),
            intArrayOf(android.R.attr.state_checked),
            intArrayOf()
        )

        val colors = intArrayOf(pressedColor, checkedColor, defaultColor)
        return ColorStateList(states, colors)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}