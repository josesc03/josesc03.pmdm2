package com.josaca.examenandroid

import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josaca.examenandroid.ui.theme.ExamenAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamenAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ExamenMain(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ExamenMain( modifier: Modifier = Modifier) {
    var nombre by rememberSaveable { mutableStateOf("") }
    var apellidos by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }
    var correo by rememberSaveable { mutableStateOf("") }
    var mostrarDisplay by rememberSaveable { mutableStateOf(false) }

    var caminarSelected by rememberSaveable { mutableStateOf(false) }
    var correrSelected by rememberSaveable { mutableStateOf(false) }
    var bicicletaSelected by rememberSaveable { mutableStateOf(false) }
    var contadorDiasCaminar by rememberSaveable { mutableStateOf(0f) }
    var contadorDiasCorrer by rememberSaveable { mutableStateOf(0f) }
    var contadorDiasBicicleta by rememberSaveable { mutableStateOf(0f) }
    var contadorGlobal = contadorDiasCaminar+contadorDiasCorrer+contadorDiasBicicleta

    var distanciaEnKm by rememberSaveable { mutableStateOf("") }
    var switchChecked by rememberSaveable { mutableStateOf(false) }

    var scroll = rememberScrollState()

    Column (modifier = modifier.verticalScroll(
        state = scroll,
        enabled = true
    ),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFAB3E3E))
            .height(75.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ThumbUp,
                contentDescription = null,
                tint = Color.White
            )

            Text(text = "RegistroActividad", textAlign = TextAlign.Center, color = Color.White, fontSize = 25.sp)
        }

        HorizontalDivider(Modifier.padding(15.dp))

        Text("REGISTRO", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(Modifier.height(15.dp))


        Image(painter = painterResource(R.drawable.perfilactividad),
            contentDescription = "Foto de perfil",
            modifier = Modifier
                .size(150.dp)
                .aspectRatio(1f)
                .clip(CircleShape)
                .border(width = 5.dp, shape = CircleShape, color = Color(0xFFAB3E3E)))

        Spacer(Modifier.height(15.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            value = nombre,
            onValueChange = {
                if (it.matches(Regex("[a-zA-Z ]+"))){
                    nombre = it
                }
            },
            label = { Text("Ingrese su nombre") }
        )

        Spacer(Modifier.height(10.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            value = apellidos,
            onValueChange = {
                if (it.matches(Regex("[a-zA-Z ]+"))){
                    apellidos = it
                }
            },
            label = { Text("Ingrese sus apellidos") }
        )

        Spacer(Modifier.height(10.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            value = correo,
            onValueChange = {
                    correo = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            label = { Text("Ingrese su correo") }
        )

        Spacer(Modifier.height(10.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            value = telefono,
            onValueChange = {
                if (it.matches(Regex("[0-9]+"))){
                    telefono = it
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            label = { Text("Ingrese su telefono") }
        )

        Spacer(Modifier.height(20.dp))

        Text("Fecha de nacimiento")

        //todo:calendario

        //todo:desplegable

        Row {
            Button(
                onClick = {
                    nombre = ""
                    apellidos = ""
                    correo = ""
                    telefono = ""
                }
            ) {
                Text("Borrar formulario")
            }

            Spacer(Modifier.width(15.dp))

            Button(
                onClick = {
                    mostrarDisplay = true
                },
                enabled = (
                        nombre != "" &&
                        apellidos != "" &&
                        correo.matches(Regex("[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+.[a-zA-Z]{1,}")) &&
                        telefono.matches(Regex("[67]{1}[0-9]{8}"))
                )
            ) {
                Text("Registrarse")
            }
        }

        if (nombre == "" ||
            apellidos == "" ||
            !correo.matches(Regex("[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+.[a-zA-Z]{1,}")) ||
            !telefono.matches(Regex("[67]{1}[0-9]{8}"))) {
            Text("Tienes errores en alguno de los campos...", color = Color.Red)
        }

        HorizontalDivider(Modifier.padding(15.dp))

        Text("ACTIVIDAD Y ESTADISTICAS", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(Modifier.height(15.dp))

        Row (horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()) {
            FilterChip(
                selected = caminarSelected,
                onClick = {
                    caminarSelected = true
                    correrSelected = false
                    bicicletaSelected = false
                },
                label = { Text("Caminar") },
            )
            FilterChip(
                selected = correrSelected,
                onClick = {
                    caminarSelected = false
                    correrSelected = true
                    bicicletaSelected = false
                },
                label = { Text("Correr") }
            )
            FilterChip(
                selected = bicicletaSelected,
                onClick = {
                    caminarSelected = false
                    correrSelected = false
                    bicicletaSelected = true
                },
                label = { Text("Bicicleta") }
            )
        }

        Spacer(Modifier.height(10.dp))

        if (caminarSelected || correrSelected || bicicletaSelected) {
            Row (Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Button(onClick = {
                    if (caminarSelected) contadorDiasCaminar--
                    if (correrSelected) contadorDiasCorrer--
                    if (bicicletaSelected) contadorDiasBicicleta--},
                    shape = RoundedCornerShape(5.dp)
                ) { Text("-") }
                Spacer(Modifier.width(10.dp))
                OutlinedCard {
                    if (caminarSelected) {
                        Text(contadorDiasCaminar.toString(), Modifier.padding(10.dp))
                    }
                    if (correrSelected) {
                        Text(contadorDiasCorrer.toString(), Modifier.padding(10.dp))
                    }
                    if (bicicletaSelected) {
                        Text(contadorDiasBicicleta.toString(), Modifier.padding(10.dp))
                    }
                }
                Spacer(Modifier.width(10.dp))
                Button(onClick = {
                    if (caminarSelected) contadorDiasCaminar++
                    if (correrSelected) contadorDiasCorrer++
                    if (bicicletaSelected) contadorDiasBicicleta++
                },
                    shape = RoundedCornerShape(5.dp),
                ) { Text("+") }
            }
            Button(onClick = {
                if (caminarSelected) contadorDiasCaminar = 0f
                if (correrSelected) contadorDiasCorrer = 0f
                if (bicicletaSelected) contadorDiasBicicleta = 0f
            }) {
                Text("Reset")
            }
        } else {
            Text("Pulsa en una actividad", color = Color.Red)
        }

        if (contadorGlobal > 0) {
            Column (Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)) {
                Text("Estadisticas globales")
                Text("Has caminado $contadorDiasCaminar dias. ${(contadorDiasCaminar / contadorGlobal)*100}%")
                Text("Has corrido $contadorDiasCorrer dias. ${(contadorDiasCorrer / contadorGlobal)*100}%")
                Text("Has usado la bicicleta $contadorDiasBicicleta dias. ${(contadorDiasBicicleta / contadorGlobal)*100}%")
            }
        }

        HorizontalDivider(Modifier.padding(15.dp))

        Text("Conversor", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(Modifier.height(15.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            value = distanciaEnKm,
            onValueChange = {
                distanciaEnKm = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            label = { Text("Ingrese una distancia en kilometros") }
        )

        Spacer(Modifier.height(15.dp))

        Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            OutlinedCard {
                Text("Metros", Modifier.padding(vertical = 15.dp, horizontal = 30.dp))
            }
            Switch(
                checked = switchChecked,
                onCheckedChange = {switchChecked = it}
            )
            OutlinedCard {
                Text("Millas", Modifier.padding(vertical = 15.dp, horizontal = 30.dp))
            }
        }

        if (distanciaEnKm != "") {
            Text("$distanciaEnKm en ${
                if (!switchChecked) "Metros es ${distanciaEnKm.toDouble() * 1000}" else "Millas es ${distanciaEnKm.toDouble()*0.621371}"
            }",
                Modifier.padding(15.dp))
        }

    }

    if (mostrarDisplay) {

        AlertDialog(
            onDismissRequest = {mostrarDisplay = false},
            confirmButton = { Button(onClick = {mostrarDisplay = false}) { Text("Aceptar") } },
            title = { Text("REGISTRO") },
            text = { Text("Te has registrado correctamente") }
        )

    }

}

@Preview(showBackground = true)
@Composable
fun ExamenMainPreview() {
    ExamenAndroidTheme {
        ExamenMain()
    }
}