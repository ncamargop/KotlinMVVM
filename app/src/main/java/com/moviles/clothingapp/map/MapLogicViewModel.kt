package com.moviles.clothingapp.map

import com.google.android.gms.maps.model.LatLng
import androidx.lifecycle.viewModelScope
import com.google.maps.android.compose.CameraPositionState
import kotlinx.coroutines.launch
import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition

class MapLogicViewModel(application: Application) : AndroidViewModel(application) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    /* User location latitude and longitude */
    private val _userLocation = MutableLiveData<LatLng>()
    val userLocation: LiveData<LatLng> get() = _userLocation

    /* Camera position (initial and to change) */
    val cameraPositionState = CameraPositionState()

    /* Default location - Mario Laserna Building */
    val defaultLocation = LatLng(4.602904573111566, -74.06503868957138)


    /* Shop fetching */
    data class Shop(val name: String, val location: LatLng, val address: String)
    private val _shopLocations = MutableLiveData<List<Shop>>()
    val shopLocations: LiveData<List<Shop>> = _shopLocations

    /* Load shops locations and User location */
    init {
        loadShops()
        getUserLocation()
    }

    private fun loadShops() {
        _shopLocations.value = listOf(
            Shop("E-Social", LatLng(4.653340400226082, -74.06102567572646), "Cra. 11 #67-46, Bogotá"),
            Shop("Planeta Vintage", LatLng(4.623326334617368, -74.06886427667965), "Cra. 13a #34-57, Bogotá"),
            Shop("El Segundazo", LatLng(4.674183693422512, -74.05288283864145), "Cl. 90 #14-45, Chapinero, Bogotá"),
            Shop("El Baulito de Mr.Bean", LatLng(4.653041858350189, -74.06386070551471), "Av. Caracas #65a-66, Bogotá"),
            Shop("Closet Up", LatLng(4.654346890637457, -74.06057896133835), "Cra. 11 #69-26, Bogotá"),
            Shop("El Cuchitril", LatLng(4.693857895240825, -74.03082026318502), "Cl. 117 #5A-13, Bogotá"),
            Shop("Herbario Vintage", LatLng(4.624205328397987, -74.07014419017378), "Cra 15 #35-12, Bogotá")
        )
    }

    /* Get user location or put it in default location */
    @SuppressLint("MissingPermission")
    fun getUserLocation() {
        viewModelScope.launch {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val userLatLng = LatLng(location.latitude, location.longitude)
                    _userLocation.value = userLatLng
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(userLatLng, 15f)
                } else {
                    _userLocation.value = defaultLocation
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(defaultLocation, 12f)
                }
            }.addOnFailureListener {
                _userLocation.value = defaultLocation
                cameraPositionState.position = CameraPosition.fromLatLngZoom(defaultLocation, 12f)
            }
        }
    }

    /* Function to focus the map view if a store from list is selected */
    fun focusOnLocation(location: LatLng) {
        viewModelScope.launch {
            cameraPositionState.animate(
                CameraUpdateFactory.newCameraPosition(
                    CameraPosition.Builder()
                        .target(location)
                        .zoom(17f)
                        .build()
                ),
                durationMs = 1000
            )
        }
    }
}
