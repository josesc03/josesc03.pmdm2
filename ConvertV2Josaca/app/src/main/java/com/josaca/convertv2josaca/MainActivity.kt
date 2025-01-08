package com.josaca.convertv2josaca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josaca.convertv2josaca.ui.theme.ConvertV2JosacaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConvertV2JosacaTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Go(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Go(modifier: Modifier = Modifier) {
    var numeroValor by rememberSaveable { mutableStateOf("") }
    var decimalBinario by rememberSaveable { mutableStateOf(false) }
    //false -> binario>decimal
    //true -> decimal>binario
    var decimalBinarioChanged by rememberSaveable { mutableStateOf(false) }


    var conversion by rememberSaveable { mutableStateOf("") }

    Column (modifier = modifier
        .fillMaxSize()
        .background(Color(10, 10, 10))
        .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Foto()

            Spacer(Modifier.width(24.dp))

            Nombre("Josaca")
        }

        HorizontalDivider(
            thickness = 5.dp,
            color = Color(0xFF303030),
            modifier = Modifier.padding(vertical = 20.dp)
        )

        Text("Introduce un número entero",
            fontSize = 24.sp,
            color = Color.White)

        Spacer(modifier = Modifier.height(20.dp))

        InputNumero(numeroValor, onNumeroValorChange = {if (it.matches(Regex("\\d*"))) numeroValor = it})

        Spacer(modifier = Modifier.height(20.dp))

        Row (verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()) {
            ADecimal(decimalBinario)
            decimalBinario = switchDecimalBinario(decimalBinario)
            ABinario(decimalBinario)
        }

        Row (modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp, horizontal = 10.dp)) {
            Text("Conversión: ", color = Color.White, textAlign = TextAlign.Left)
            Text(conversion, color = Color(if (decimalBinarioChanged) 0xFF6cb1d8 else 0xFF6fd86c))
        }

        Row (modifier = Modifier.fillMaxWidth(.8f),horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                if (numeroValor != "") {
                    if (numeroValor.matches(Regex("[0,1]+"))) {
                        conversion = if (decimalBinario) numeroValor.toInt().toString(2) else numeroValor.toInt(2).toString()
                        decimalBinarioChanged = decimalBinario
                    } else {
                        conversion = if (decimalBinario) numeroValor.toInt().toString(2) else ""
                        decimalBinarioChanged = decimalBinario
                    }
                }
            },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color(48,48,48)
                )) {
                Icon(painter = painterResource(id = R.drawable.baseline_autorenew_24), tint = Color.White, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text("Convertir")
            }

            AnimatedVisibility(
                visible = numeroValor.isNotEmpty(),
                enter = slideInHorizontally(
                    initialOffsetX = {-it},
                    animationSpec = tween(500)) + fadeIn(animationSpec = tween(500)
                ),
                exit = slideOutHorizontally(
                    targetOffsetX = {-it},
                    animationSpec = tween(500)) + fadeOut( animationSpec = tween(500)
                )
            ) {
                Button(onClick = {
                    numeroValor = ""
                    conversion = ""
                },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        disabledContentColor = Color(0xFFBBBBBB),

                        containerColor = Color(0xFFc81a1a) ,
                        disabledContainerColor = Color(0xFFa24444)
                    ),
                    enabled = numeroValor.isNotEmpty(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(imageVector = Icons.Default.Delete, tint = if (numeroValor.isNotEmpty()) Color.White else Color(0xFFBBBBBB), contentDescription = null)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Reset")
                }
            }
        }


    }

}

@Composable
fun switchDecimalBinario(decimalBinario: Boolean): Boolean {
    var decimalBinario1 by rememberSaveable { mutableStateOf(decimalBinario) }
    Switch(checked = decimalBinario1,
        onCheckedChange = { decimalBinario1 = it },
        colors = SwitchDefaults.colors(
            checkedTrackColor = Color(225, 225, 225),
            uncheckedTrackColor = Color(225, 225, 225),

            checkedBorderColor = Color(180, 180, 180),
            uncheckedBorderColor = Color(180, 180, 180),

            checkedThumbColor = Color(125, 125, 125),
            uncheckedThumbColor = Color(125, 125, 125)
        ),
        thumbContent = {
            Box {
                Canvas(modifier = Modifier.size(40.dp)) {
                    drawCircle(
                        color = Color(48, 48, 48),
                        radius = 40f
                    )
                }
                Icon(
                    imageVector = if (decimalBinario1) Icons.AutoMirrored.Filled.ArrowForward else Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }

        })
    return decimalBinario1
}

@Composable
fun ABinario(decimalBinario: Boolean) {
    Text(
        "a binario",
        color = if (decimalBinario) Color.White else Color(150, 150, 150),
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if (decimalBinario) Color(48, 48, 48) else Color(20, 20, 20))
            .padding(vertical = 10.dp, horizontal = 15.dp)
    )
}

@Composable
fun ADecimal(decimalBinario: Boolean) {
    Text(
        "a decimal",
        color = if (!decimalBinario) Color.White else Color(150, 150, 150),
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if (!decimalBinario) Color(48, 48, 48) else Color(20, 20, 20))
            .padding(vertical = 10.dp, horizontal = 15.dp)
    )
}

@Composable
fun InputNumero(numeroValor: String, onNumeroValorChange: (String) -> Unit) {
    TextField(
        value = numeroValor,
        onValueChange = onNumeroValorChange,
        label = { Text("Número a convertir") },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_numbers_24),
                contentDescription = null,
                tint = Color(150, 150, 150)
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFF3c3c3c),
            focusedContainerColor = Color(0xFF373737),
            disabledContainerColor = Color(30,30,30),
            unfocusedLabelColor = Color(150,150,150),
            focusedLabelColor = Color(200,200,200),
            disabledLabelColor = Color(50,50,50),
            focusedIndicatorColor = Color.White,
            focusedTextColor = Color.White
        ),
    )
}

@Composable
fun Foto() {
    Image(painter = painterResource(id = R.drawable.profile),
        contentDescription = "Foto de perfil",
        modifier = Modifier
            .border(color = Color(0xFF303030), width = 5.dp, shape = CircleShape)
            .clip(shape = CircleShape)
    )

}

@Composable
fun Nombre(nombre: String) {
    Text(nombre,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
        color = Color.White)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GoPreview() {
    ConvertV2JosacaTheme {
        Go()
    }
}