package com.example.myapplicationnextzyroom

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val locale: String
//        locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            this.resources.configuration.locales.get(0).country
//        } else {
//            this.resources.configuration.locale.country
//        }
//        Log.i("country", "country code: $locale")

        setupActionBarWithNavController(findNavController(R.id.fragment))
    }

    // activate back button in navbar
    override fun onSupportNavigateUp(): Boolean {
        val navControl = findNavController(R.id.fragment)
        return navControl.navigateUp() || super.onSupportNavigateUp()
    }
}