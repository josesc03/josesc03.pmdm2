package com.josaca.ejerciciosud03p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josaca.ejerciciosud03p2.ui.theme.EjerciciosUD03P2Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjerciciosUD03P2Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
                    Go(
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Go(modifier: Modifier = Modifier) {
    var randomInt by rememberSaveable { mutableIntStateOf(-1) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(0.dp, 33.dp, 0.dp, 0.dp)
            .background(Color(0xFFF1F6A3))

    ) {
        Box(modifier = Modifier.height(210.dp)) {
            Text(
                text = if (randomInt == -1) "" else randomInt.toString(),
                color = Color(0xFFBAC246),
                fontSize = 200.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row {
            Button(
                onClick = { randomInt = Random.nextInt(100) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF84DE47)
                ),
                shape = RoundedCornerShape(10.dp),
            )
            {
                Text("Generar número")
            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = {
                    randomInt = -1
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD56262)
                ),
                shape = RoundedCornerShape(10.dp),
                enabled = (randomInt != -1)
            ) {
                Icon(
                    Icons.Filled.Refresh,
                    contentDescription = "Reset"
                )
                Text(text = "Reset")
            }

        }
    }
}
