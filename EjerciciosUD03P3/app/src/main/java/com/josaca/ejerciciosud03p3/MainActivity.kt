package com.josaca.ejerciciosud03p3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josaca.ejerciciosud03p3.ui.theme.EjerciciosUD03P3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjerciciosUD03P3Theme {
                Scaffold { innerPadding ->
                    Go(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Go(modifier: Modifier = Modifier) {

    var puntosA by rememberSaveable { mutableIntStateOf(0) }
    var juegosA by rememberSaveable { mutableIntStateOf(0) }
    var setsA by rememberSaveable { mutableIntStateOf(0) }

    var puntosB by rememberSaveable { mutableIntStateOf(0) }
    var juegosB by rememberSaveable { mutableIntStateOf(0) }
    var setsB by rememberSaveable { mutableIntStateOf(0) }

    var deuce by rememberSaveable { mutableStateOf(false) }
    var ventajaA by rememberSaveable { mutableStateOf(false) }
    var ventajaB by rememberSaveable { mutableStateOf(false) }

    var tieBreak by rememberSaveable { mutableStateOf(false) }

    var ganadorA by rememberSaveable { mutableStateOf(false) }
    var ganadorB by rememberSaveable { mutableStateOf(false) }



    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 25.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "SETS", modifier = Modifier
                .fillMaxWidth()
                .background(Color(255, 195, 81, 255))
                .padding(5.dp),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            color = Color.White
        )

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(setsA.toString(), fontSize = 80.sp, color = Color(58, 146, 217, 255))
            Text(setsB.toString(), fontSize = 80.sp, color = Color(58, 217, 116, 255))
        }

        Text(
            "JUEGOS", modifier = Modifier
                .fillMaxWidth()
                .background(Color(255, 195, 81, 255))
                .padding(5.dp),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            color = Color.White
        )
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(juegosA.toString(), fontSize = 80.sp, color = Color(58, 146, 217, 255))
            Text(juegosB.toString(), fontSize = 80.sp, color = Color(58, 217, 116, 255))
        }

        Text(
            "SET ACTUAL", modifier = Modifier
                .fillMaxWidth()
                .background(Color(255, 195, 81, 255))
                .padding(5.dp),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            color = Color.White
        )
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                when {
                    (puntosA == 0 && !tieBreak && !deuce) -> "0"
                    (puntosA == 1 && !tieBreak && !deuce) -> "15"
                    (puntosA == 2 && !tieBreak && !deuce) -> "30"
                    (puntosA == 3 && !tieBreak && !deuce) -> "40"
                    else -> if (deuce) if (ventajaA) "40v" else "40" else if (tieBreak) puntosA.toString() else "ERROR"
                }, fontSize = 80.sp, color = Color(58, 146, 217, 255)
            )
            Text(
                when {
                    (puntosB == 0 && !tieBreak && !deuce) -> "0"
                    (puntosB == 1 && !tieBreak && !deuce) -> "15"
                    (puntosB == 2 && !tieBreak && !deuce) -> "30"
                    (puntosB == 3 && !tieBreak && !deuce) -> "40"
                    else -> if (deuce) if (ventajaB) "40v" else "40" else if (tieBreak) puntosB.toString() else "ERROR"
                }, fontSize = 80.sp, color = Color(82, 217, 58, 255)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    if (deuce) {
                        if (ventajaA) {
                            deuce = false
                            ventajaB = false
                            ventajaA = false
                            juegosA++
                        } else {
                            ventajaA = true
                            ventajaB = false
                        }
                    } else if (tieBreak) {
                        puntosA++
                        if (puntosA >= 7 && (puntosA - puntosB) > 1) {
                            setsA++
                            tieBreak = false
                            puntosB = 0
                            puntosA = 0
                            juegosA = 0
                            juegosB = 0
                        }
                    } else {
                        puntosA++
                        if (puntosA == 4) {
                            puntosA = 0
                            puntosB = 0
                            juegosA++

                            if (juegosA >= 6 && (juegosA - juegosB) > 1) {
                                juegosA = 0
                                juegosB = 0
                                setsA++
                            }
                        }
                    }
                    if (setsA == 2) {
                        ganadorA = true
                    }
                }, colors = ButtonDefaults.buttonColors(Color(58, 146, 217, 255))) {
                    Text("PUNTO EQUIPO A", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Button(onClick = {
                    if (deuce) {
                        if (ventajaB) {
                            deuce = false
                            ventajaB = false
                            ventajaA = false

                            juegosB++
                        } else {
                            ventajaB = true
                            ventajaA = false
                        }
                    } else if (tieBreak) {
                        puntosB++
                        if (puntosB >= 7 && (puntosB - puntosA) > 1) {
                            setsB++
                            tieBreak = false
                            puntosB = 0
                            puntosA = 0
                            juegosA = 0
                            juegosB = 0
                        }
                    } else {
                        puntosB++
                        if (puntosB == 4) {
                            puntosB = 0
                            puntosA = 0
                            juegosB++
                            if (juegosB >= 6 && (juegosB - juegosA) > 1) {
                                juegosB = 0
                                juegosA = 0
                                setsB++

                            }
                        }
                    }
                    if (setsB == 2) {
                        ganadorB = true
                    }
                }, colors = ButtonDefaults.buttonColors(Color(82, 217, 58, 255))) {
                    Text("PUNTO EQUIPO B", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }

            Button(
                onClick = {
                    puntosA = 0
                    puntosB = 0

                    juegosA = 0
                    juegosB = 0

                    setsA = 0
                    setsB = 0

                    deuce = false

                },
                colors = ButtonDefaults.buttonColors(Color(225, 71, 71, 255)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Text("RESETEAR", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

    }


    if (juegosA == 6 && juegosB == 6 && !tieBreak) {
        AlertDialog(
            onDismissRequest = {

            },
            title = {
                Text(text = "TIE-BREAK")
            },
            text = {
                Text("Los dos juegos han llegado a 6")
            },
            confirmButton = {
                Button(
                    onClick = {
                        tieBreak = true
                        puntosA = 0
                        puntosB = 0
                    }
                ) {
                    Text("Aceptar")
                }
            }
        )
    }

    if (puntosA == 3 && puntosB == 3 && !deuce && !tieBreak) {
        AlertDialog(
            onDismissRequest = {

            },
            title = {
                Text(text = "DEUCE")
            },
            text = {
                Text("Los dos sets han llegado a 40")
            },
            confirmButton = {
                Button(
                    onClick = {
                        deuce = true
                        puntosA = 0
                        puntosB = 0
                    }
                ) {
                    Text("Aceptar")
                }
            }
        )
    }

    if (ganadorA || ganadorB) {
        AlertDialog(
            onDismissRequest = {

            },
            title = {
                Text(text = "Has ganado " + if (ganadorA) "equipo A" else "equipo B")
            },
            text = {
                Text("¡Enhorabuena " + if (ganadorA) "equipo A" else "equipo B" + " por ganar!")
            },
            confirmButton = {
                Button(
                    onClick = {
                        puntosA = 0
                        puntosB = 0

                        juegosA = 0
                        juegosB = 0

                        setsA = 0
                        setsB = 0

                        ganadorB = false
                        ganadorA = false
                    }
                ) {
                    Text("Empezar de nuevo")
                }
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GoPreview() {
    EjerciciosUD03P3Theme {
        Scaffold { innerPadding ->
            Go(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
    }
}