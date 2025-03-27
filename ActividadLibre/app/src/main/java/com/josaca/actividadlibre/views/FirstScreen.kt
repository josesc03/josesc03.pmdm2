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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.josaca.actividadlibre.R
import com.josaca.actividadlibre.navigation.Routes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(navController: NavHostController) {
    var mostrarDialogoHistorial by rememberSaveable { mutableStateOf(false) }
    var mostrarDialogoParametros by rememberSaveable { mutableStateOf(false) }
    var checkboxCheck by rememberSaveable { mutableStateOf(false) }
    var peso by rememberSaveable { mutableStateOf("") }
    var altura by rememberSaveable { mutableStateOf("") }
    var selectedUnit by rememberSaveable { mutableStateOf("KG") }

    Scaffold(topBar = {
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
                    Text("Calculadora de IMC", fontWeight = FontWeight.Bold, color = Color.White)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xffaf2525)
            )
        )
    }, bottomBar = {
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
                    bottom = 15.dp,
                    start = 15.dp,
                    end = 15.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(R.drawable.heart_logo), contentDescription = null,
                    Modifier.size(100.dp)
                )
                Text(
                    modifier = Modifier.width(300.dp),
                    text = "Bienvenid@ a la calculadora de IMC",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }

            HorizontalDivider(Modifier.padding(vertical = 20.dp))

            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = "Peso:",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 5.dp)
                )

                TextField(
                    value = peso,
                    onValueChange = {
                        if (it.matches(Regex("[0-9]+")) || it.isEmpty()) {
                            peso = it
                        }
                    },
                    label = { Text("Ingrese su peso en $selectedUnit") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ), modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        Image(
                            painter = painterResource(R.drawable.weight),
                            contentDescription = null,
                            Modifier.size(25.dp)
                        )
                    }

                )
            }

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                FilterChip(
                    selected = selectedUnit == "KG",
                    onClick = { selectedUnit = "KG" },
                    label = {
                        Text(
                            "KG",
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    leadingIcon = if (selectedUnit == "KG") {
                        { Icon(Icons.Default.Check, contentDescription = null) }
                    } else null,
                    modifier = Modifier.width(80.dp)
                )

                // Chip para Libras
                FilterChip(
                    selected = selectedUnit == "LB",
                    onClick = { selectedUnit = "LB" },
                    label = {
                        Text(
                            "LB",
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    leadingIcon = if (selectedUnit == "LB") {
                        { Icon(Icons.Default.Check, contentDescription = null) }
                    } else null,
                    modifier = Modifier.width(80.dp)
                )
            }

            Spacer(Modifier.height(10.dp))

            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = "Altura:",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 20.dp, bottom = 5.dp)
                )

                TextField(
                    value = altura,
                    onValueChange = {
                        if (it.matches(Regex("[0-9]+")) || it.isEmpty()) {
                            altura = it
                        }
                    },
                    label = { Text("Ingrese su altura en CM") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ), modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        Image(
                            painter = painterResource(R.drawable.height),
                            contentDescription = null,
                            Modifier.size(25.dp)
                        )
                    }

                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkboxCheck,
                    onCheckedChange = { checkboxCheck = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xffaf2525)
                    )
                )
                Text(
                    "Al continuar, aceptas nuestros Términos y Condiciones y nuestra Política de Privacidad.*",
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }

            Button(
                onClick = {
                    if (peso.isNotEmpty() && altura.isNotEmpty() && checkboxCheck == true) {
                        navController.navigate(
                            Routes.SecondScreen.createRoute(
                                peso.toInt(),
                                altura.toInt(),
                                selectedUnit
                            )
                        )
                    } else {
                        mostrarDialogoParametros = true
                    }
                }, Modifier.padding(top = 20.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xffaf2525),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("Mostrar IMC")
            }

        }
    }
    AlertaFaltanParametros(
        mostrarDialogoParametros = mostrarDialogoParametros,
        onDismiss = { mostrarDialogoParametros = false })
    AlertaHistorial(
        mostrarDialogoHistorial = mostrarDialogoHistorial,
        onDismiss = { mostrarDialogoHistorial = false })

}

@Composable
fun AlertaFaltanParametros(mostrarDialogoParametros: Boolean, onDismiss: () -> Unit) {
    if (mostrarDialogoParametros) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Aviso") },
            text = { Text(text = "Todos los campos deben estar rellenados.") },
            confirmButton = {
                Button(
                    onClick = { onDismiss() }, shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xffaf2525),
                        contentColor = Color.White
                    )
                ) {
                    Text("Aceptar")
                }
            }
        )
    }
}

@Composable
fun AlertaHistorial(mostrarDialogoHistorial: Boolean, onDismiss: () -> Unit) {
    if (mostrarDialogoHistorial) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Aviso") },
            text = { Text(text = "Esta función no está implementada.") },
            confirmButton = {
                Button(
                    onClick = { onDismiss() }, shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xffaf2525),
                        contentColor = Color.White
                    )
                ) {
                    Text("Aceptar")
                }
            }
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFirstScreen() {
    val navController = rememberNavController() // Simula un NavController
    FirstScreen(navController)
}