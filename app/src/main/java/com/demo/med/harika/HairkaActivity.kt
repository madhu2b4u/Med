package com.demo.med.harika

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.demo.med.R
import com.demo.med.databinding.ActivityHairkaBinding
import com.demo.med.harika.ui.dashboard.DashboardFragment
import com.demo.med.harika.ui.home.HomeFragment
import com.demo.med.harika.ui.notifications.NotificationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HairkaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHairkaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityHairkaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navView: BottomNavigationView = binding.bottomNavigation

        // Load the initial fragment
        loadFragment(HomeFragment())

        navView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_dashboard -> {
                    loadFragment(DashboardFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_notifications -> {
                    loadFragment(NotificationsFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                else -> false
            }
        }


    }


    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }
}