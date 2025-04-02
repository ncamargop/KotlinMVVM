package com.moviles.clothingapp

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.moviles.clothingapp.navigation.AppNavigation
import com.moviles.clothingapp.login.LoginViewModel
import com.moviles.clothingapp.login.ResetPasswordViewModel
import com.moviles.clothingapp.weatherBanner.WeatherViewModel
import android.Manifest
import android.os.Build
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.perf.FirebasePerformance
import com.moviles.clothingapp.cart.CartViewModel

/* The mainActivity initializes all the app */
class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var resetPasswordViewModel: ResetPasswordViewModel
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private var locationPermissionGranted = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* Initialize Firebase services */
        auth = Firebase.auth
        firebaseAnalytics = Firebase.analytics


        /* Initialize Login ViewModel */
        loginViewModel = LoginViewModel(auth)
        resetPasswordViewModel = ResetPasswordViewModel(auth)
        weatherViewModel = WeatherViewModel(this, hasLocationPermission = false)
        val cartViewModel: CartViewModel by viewModels()



        setContent {
            val navController = rememberNavController()
            AppNavigation(navController, loginViewModel, resetPasswordViewModel, weatherViewModel, cartViewModel)
        }

        /* Log device information and metrics for firebase */
        logDeviceInfo()

        /* Request location permissions */
        requestLocationPermissions()


    }


    /* Auxiliary function to record device info in firebase */
    private fun logDeviceInfo() {
        Log.d("FirebasePerf", "Firebase Performance Monitoring initialized: ${FirebasePerformance.getInstance()}")
        val deviceInfo = Bundle().apply {
            putString("device_model", Build.MODEL)
            putString("device_brand", Build.BRAND)
            putString("os_version", Build.VERSION.RELEASE)
        }

        firebaseAnalytics.logEvent("device_info", deviceInfo)
        Log.d("DEVICES", deviceInfo.toString())
    }

    /* Auxiliary functions to request location permissions and initialize weatherViewModel to fetch data */
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val permissionGranted = permissions.entries.all { it.value }
        weatherViewModel.updateLocationPermission(permissionGranted)
    }

    private fun requestLocationPermissions() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                // Permissions already granted
                weatherViewModel.updateLocationPermission(true)
            }
            else -> {
                // Request permissions
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }
    }

}