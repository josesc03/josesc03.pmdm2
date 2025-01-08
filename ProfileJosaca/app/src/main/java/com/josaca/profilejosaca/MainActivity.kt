package com.josaca.profilejosaca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.josaca.profilejosaca.ui.theme.ProfileJosacaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileJosacaTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    Go(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun Go(modifier: Modifier = Modifier) {
    var suscrito by rememberSaveable { mutableStateOf(false) }
    var cantidadCorreos by rememberSaveable { mutableIntStateOf(0) }

    var isLiked1 by rememberSaveable { mutableStateOf(false) }
    var isLiked2 by rememberSaveable { mutableStateOf(false) }


    Column(
        modifier = modifier.background(Color(246, 246, 246)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(width = 3.dp, Color(60, 60, 60), CircleShape)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    "Josaca", fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(
                        35, 35, 35
                    )
                )
                Row(
                    modifier = Modifier.width(225.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { suscrito = !suscrito },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (!suscrito) Color(60, 60, 60) else Color(
                                100,
                                100,
                                100
                            )
                        ),
                        modifier = Modifier.width(140.dp)
                    ) {
                        Image(
                            painter = painterResource(id = if (!suscrito) R.drawable.person_add else R.drawable.person_remove),
                            colorFilter = ColorFilter.tint(Color(250, 250, 250)),
                            contentDescription = "Person_add"
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            if (!suscrito) "Follow" else "Unfollow",
                            color = Color(250, 250, 250)
                        )
                    }

                    BadgedBox(badge = {
                        if (cantidadCorreos > 0) {
                            Badge(
                                containerColor = Color.Red,
                                contentColor = Color.White
                            ) {
                                Text(
                                    if (cantidadCorreos >= 100) "99+" else cantidadCorreos.toString(),
                                    fontSize = 13.sp,
                                    modifier = Modifier.padding(2.dp)
                                )
                            }
                        }
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.email),
                            colorFilter = ColorFilter.tint(Color(60, 60, 60)),
                            contentDescription = "Correo",
                            modifier = Modifier.size(40.dp)
                        )

                    }

                }
            }
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = Color(60, 60, 60),
            modifier = Modifier.padding(10.dp)
        )

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color(235, 235, 235))
                .width(300.dp)
                .padding(20.dp)

        ) {
            Text(
                "Hobbies",
                color = Color(60, 60, 60),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Row {
                Image(
                    painter = painterResource(id = R.drawable.videogame),
                    contentDescription = "videojuegos",
                    colorFilter = ColorFilter.tint(Color(60, 60, 60))
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    "Videojuegos",
                    color = Color(60, 60, 60)
                )
            }
            Row {
                Image(
                    painter = painterResource(id = R.drawable.manga_video),
                    contentDescription = "anime",
                    colorFilter = ColorFilter.tint(Color(60, 60, 60))
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    "Anime",
                    color = Color(60, 60, 60)
                )
            }
            Row {
                Image(
                    painter = painterResource(id = R.drawable.programar),
                    contentDescription = "programar",
                    colorFilter = ColorFilter.tint(Color(60, 60, 60))
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    "Programar",
                    color = Color(60, 60, 60)
                )
            }
            Row {
                Image(
                    painter = painterResource(id = R.drawable.square_3d),
                    contentDescription = "3D",
                    colorFilter = ColorFilter.tint(Color(60, 60, 60))
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    "Modelar en 3D",
                    color = Color(60, 60, 60)
                )
            }
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = Color(60, 60, 60),
            modifier = Modifier.padding(10.dp)
        )


        //terminar
        Row(modifier = Modifier.width(370.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.paisaje1),
                    contentDescription = "paisaje1",
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .size(175.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = painterResource(if (!isLiked1) R.drawable.notlike else R.drawable.like),
                    contentDescription = if (!isLiked1) "not like" else "like",
                    modifier = Modifier
                        .zIndex(1f)
                        .align(Alignment.BottomEnd)
                        .size(45.dp)
                        .padding(5.dp)
                        .clickable { isLiked1 = !isLiked1 },
                    colorFilter = ColorFilter.tint(
                        if (!isLiked1) Color.White else Color(229, 101, 101)
                    )
                )
            }

            Box {
                Image(
                    painter = painterResource(id = R.drawable.paisaje2),
                    contentDescription = "paisaje2",
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .size(175.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = painterResource(if (!isLiked2) R.drawable.notlike else R.drawable.like),
                    contentDescription = if (!isLiked2) "not like" else "like",
                    modifier = Modifier
                        .zIndex(1f)
                        .align(Alignment.BottomEnd)
                        .size(45.dp)
                        .padding(5.dp)
                        .clickable { isLiked2 = !isLiked2 },
                    colorFilter = ColorFilter.tint(
                        if (!isLiked2) Color.White else Color(229, 101, 101)
                    )
                )
            }
        }
        //

        HorizontalDivider(
            thickness = 1.dp,
            color = Color(60, 60, 60),
            modifier = Modifier.padding(10.dp)
        )

        Button(
            onClick = {
                cantidadCorreos++
            },
            border = BorderStroke(width = 3.dp, color = Color(60, 60, 60)),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color(60, 60, 60)
            )
        ) {
            Text("+1")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GoPreview() {
    ProfileJosacaTheme {
        Go()
    }
}