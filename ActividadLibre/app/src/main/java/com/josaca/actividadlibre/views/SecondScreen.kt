package com.josaca.actividadlibre.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.josaca.actividadlibre.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navController: NavHostController, peso: Int, altura: Int, unidad: String) {
    val pesoState by rememberSaveable { mutableStateOf(peso) }
    val alturaState by rememberSaveable { mutableStateOf(altura) }
    val unidadState by rememberSaveable { mutableStateOf(unidad) }
    var imcResult by rememberSaveable { mutableStateOf(0.0) }
    var categoria by rememberSaveable { mutableStateOf("") }
    var mostrarDialogoHistorial by rememberSaveable { mutableStateOf(false) }


    // Cálculo del IMC
    LaunchedEffect(pesoState, alturaState, unidadState) {
        imcResult = calcularIMC(pesoState.toDouble(), alturaState.toDouble(), unidadState)
        categoria = interpretarIMC(imcResult)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(100.dp),
                title = {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Absolute.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.muscle),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            "Calculadora de IMC",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xffaf2525)
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = {
                        Image(
                            painter = painterResource(R.drawable.baseline_history_24),
                            contentDescription = null
                        )
                    },
                    label = { Text("Historial") },
                    selected = false,
                    onClick = {
                        mostrarDialogoHistorial = true
                    }
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(),
                    start = 15.dp,
                    bottom = 15.dp,
                    end = 15.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "RESULTADOS",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Image(
                painter = painterResource(id = R.drawable.ic_imc_placeholder), // Pon tu imagen aquí
                contentDescription = "Imagen explicativa del IMC",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )

            Text(
                text = "IMC: %.2f".format(imcResult),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "Categoría: $categoria",
                fontSize = 18.sp,
                color = Color.Gray
            )
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(top = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xffaf2525),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("Volver")
            }
        }
    }
    AlertaHistorial(
        mostrarDialogoHistorial = mostrarDialogoHistorial,
        onDismiss = { mostrarDialogoHistorial = false })
}


fun calcularIMC(peso: Double, alturaCm: Double, unidadPeso: String): Double {
    val alturaM = alturaCm / 100  // Convertir cm a metros
    val pesoEnKg =
        if (unidadPeso == "LB") peso * 0.453592 else peso // Convertir LB a KG si es necesario
    return pesoEnKg / (alturaM * alturaM)
}

fun interpretarIMC(imc: Double): String {
    return when {
        imc < 18.5 -> "Bajo peso"
        imc < 25.0 -> "Peso normal"
        imc < 30.0 -> "Sobrepeso"
        imc < 35.0 -> "Obesidad grado 1"
        imc < 40.0 -> "Obesidad grado 2"
        else -> "Obesidad grado 3"
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSecondScreen() {
    SecondScreen(navController = rememberNavController(), peso = 70, altura = 175, unidad = "KG")
}
