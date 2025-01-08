package com.josaca.testing

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.josaca.testing.ui.theme.TestingTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        setContent {
            TestingTheme {
                LocationScreen()
            }
        }
    }

    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
        }
    }

        private fun getLocation(onLocationReceived: (String, String) -> Unit) {
        if (checkLocationPermission()) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    val latitude = it.latitude
                    val longitude = it.longitude
                    CoroutineScope(Dispatchers.IO).launch {
                        val city = getCityFromCoordinates(latitude, longitude)
                        val coordinates = "Lat: $latitude, Lon: $longitude"

                        withContext(Dispatchers.Main) {
                            onLocationReceived(coordinates, city)
                        }
                    }
                } ?: run {
                    onLocationReceived("Ubicación no disponible", "Ciudad no encontrada")
                }
            }
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private suspend fun getCityFromCoordinates(lat: Double, lon: Double): String {
        return withContext(Dispatchers.IO) {
            try {
                val geocoder = Geocoder(this@MainActivity, Locale.getDefault())
                val addresses = geocoder.getFromLocation(lat, lon, 1)
                addresses?.get(0)?.locality ?: "Ciudad no encontrada"
            } catch (e: Exception) {
                "Error al obtener la ciudad"
            }
        }
    }

    @Composable
    fun LocationScreen() {
        val coordinates = remember { mutableStateOf("Esperando ubicación...") }
        val city = remember { mutableStateOf("Esperando ciudad...") }
        val loading = remember { mutableStateOf(false) }

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Text(text = "Coordenadas:")
                Text(text = coordinates.value, modifier = Modifier.padding(top = 8.dp))
                Text(text = "Ciudad:")
                Text(text = city.value, modifier = Modifier.padding(top = 8.dp))

                Button(
                    onClick = {
                        loading.value = true
                        getLocation { loc, cityName ->
                            coordinates.value = loc
                            city.value = cityName
                            loading.value = false
                        }
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = "Obtener ubicación")
                }

                if (loading.value) {
                    CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
                }
            }
        }
    }
}