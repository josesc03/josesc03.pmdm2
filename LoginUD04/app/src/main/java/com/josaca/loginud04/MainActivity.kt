package com.josaca.loginud04

import android.content.res.Configuration
import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.josaca.loginud04.ui.theme.LoginUD04Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginUD04Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(modifier: Modifier = Modifier) {
    LocalFocusManager.current

    var emailValue by rememberSaveable { mutableStateOf("") }
    var emailCheck by rememberSaveable { mutableStateOf(false) }

    var nameValue by rememberSaveable { mutableStateOf("") }
    var nameCheck by rememberSaveable { mutableStateOf(false) }

    var firstSurnameValue by rememberSaveable { mutableStateOf("") }
    var firstSurnameCheck by rememberSaveable { mutableStateOf(false) }

    var secondSurnameValue by rememberSaveable { mutableStateOf("") }
    var secondSurnameCheck by rememberSaveable { mutableStateOf(false) }

    var phoneValue by rememberSaveable { mutableStateOf("") }
    var phoneCheck by rememberSaveable { mutableStateOf(false) }

    var dateOfBirth =
        rememberDatePickerState(initialSelectedDateMillis = Calendar.getInstance().timeInMillis)
    var showDatePickerDialog by rememberSaveable { mutableStateOf(false) }

    var checks = (emailCheck && nameCheck && firstSurnameCheck && phoneCheck)

    var errors by rememberSaveable { mutableStateOf(false) }

    //CHIPS//
    var selectedFilterChip01 by rememberSaveable { mutableStateOf(false) }
    var selectedFilterChip02 by rememberSaveable { mutableStateOf(false) }
    var selectedFilterChip03 by rememberSaveable { mutableStateOf(false) }
    var selectedFilterChip04 by rememberSaveable { mutableStateOf(false) }
    var selectedFilterChip05 by rememberSaveable { mutableStateOf(false) }
    var selectedFilterChip06 by rememberSaveable { mutableStateOf(false) }

    var showDialog by rememberSaveable { mutableStateOf(false) }


    Box(modifier = modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .zIndex(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Regístrate en la APP",
                modifier = Modifier
                    .background(Color(0xFF3EC745))
                    .padding(15.dp)
                    .fillMaxWidth(),
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            if (errors) {

                Text(
                    "Existen errores, revisa los campos",
                    modifier = Modifier
                        .background(Color.Red)
                        .padding(10.dp)
                        .fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

            }
        }


        Column(
            Modifier
                .fillMaxSize()
                .padding(15.dp)
                .verticalScroll(rememberScrollState()),
        ) {

            if (errors) Spacer(Modifier.height(125.dp)) else Spacer(Modifier.height(80.dp))

            Text("Email", fontWeight = FontWeight.SemiBold, fontSize = 25.sp)
            TextField(
                label = { Text("Introduce tu email") },
                value = emailValue,
                onValueChange = {
                    emailValue = it
                    emailCheck =
                        emailValue.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$"))
                },
                placeholder = { Text("user@domain.com") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon"
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = if (!emailCheck) Color(0xFFFF6B6B) else MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(Modifier.height(15.dp))

            Text("Nombre", fontWeight = FontWeight.SemiBold, fontSize = 25.sp)
            TextField(
                label = { Text("Introduce tu nombre") },
                value = nameValue,
                onValueChange = {
                    nameValue = it
                    nameCheck =
                        nameValue.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{1,32}$"))
                },
                placeholder = { Text("Letras y espacios, máximo 15 caracteres") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = if (!nameCheck) Color(0xFFFF6B6B) else MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(Modifier.height(15.dp))

            Text("Primer Apellido", fontWeight = FontWeight.SemiBold, fontSize = 25.sp)
            TextField(
                label = { Text("Introduce tu primer apellido") },
                value = firstSurnameValue,
                onValueChange = {
                    firstSurnameValue = it
                    firstSurnameCheck =
                        firstSurnameValue.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{1,32}$"))
                },
                placeholder = { Text("Letras y espacios, máximo 15 caracteres") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = if (!firstSurnameCheck) Color(0xFFFF6B6B) else MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(Modifier.height(15.dp))

            Text("Segundo Apellido", fontWeight = FontWeight.SemiBold, fontSize = 25.sp)
            TextField(
                label = { Text("Introduce tu segundo apellido") },
                value = secondSurnameValue,
                onValueChange = {
                    secondSurnameValue = it
                    secondSurnameCheck =
                        secondSurnameValue.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{0,32}$"))
                },
                placeholder = { Text("Letras y espacios, máximo 15 caracteres") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = if (!secondSurnameCheck) Color(0xFFFF6B6B) else MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(Modifier.height(15.dp))

            Text("Telefono", fontWeight = FontWeight.SemiBold, fontSize = 25.sp)
            TextField(
                label = { Text("Introduce tu telefono") },
                value = phoneValue,
                onValueChange = {
                    phoneValue = it
                    phoneCheck =
                        phoneValue.matches(Regex("^\\+?[1-9]\\d{7,13}$"))
                },
                placeholder = { Text("123456789") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Phone Icon"
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = if (!phoneCheck) Color(0xFFFF6B6B) else MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(Modifier.height(15.dp))

            Text("Fecha de nacimiento", fontWeight = FontWeight.SemiBold, fontSize = 25.sp)
            Button(
                onClick = { showDatePickerDialog = true },
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(text = "Seleccionar Fecha de Nacimiento")
            }

            Spacer(Modifier.height(15.dp))

            Text("Selecciona tus favoritos", fontWeight = FontWeight.SemiBold, fontSize = 25.sp)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp)
            ) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    FilterChip(modifier = Modifier.width(115.dp),
                        selected = selectedFilterChip01,
                        onClick = { selectedFilterChip01 = !selectedFilterChip01 },
                        label = {
                            Text(
                                "Aventura",
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        leadingIcon = {
                            if (selectedFilterChip01) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Checked",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        }
                    )
                    FilterChip(modifier = Modifier.width(115.dp),
                        selected = selectedFilterChip02,
                        onClick = { selectedFilterChip02 = !selectedFilterChip02 },
                        label = {
                            Text(
                                "Acción",
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        leadingIcon = {
                            if (selectedFilterChip02) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Checked",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        }
                    )
                    FilterChip(modifier = Modifier.width(115.dp),
                        selected = selectedFilterChip03,
                        onClick = { selectedFilterChip03 = !selectedFilterChip03 },
                        label = {
                            Text(
                                "Fantasia",
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        leadingIcon = {
                            if (selectedFilterChip03) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Checked",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        }
                    )
                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FilterChip(modifier = Modifier.width(115.dp),
                        selected = selectedFilterChip04,
                        onClick = { selectedFilterChip04 = !selectedFilterChip04 },
                        label = {
                            Text(
                                "Terror",
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        leadingIcon = {
                            if (selectedFilterChip04) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Checked",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        }
                    )
                    FilterChip(modifier = Modifier.width(115.dp),
                        selected = selectedFilterChip05,
                        onClick = { selectedFilterChip05 = !selectedFilterChip05 },
                        label = {
                            Text(
                                "Suspense",
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        leadingIcon = {
                            if (selectedFilterChip05) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Checked",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        }
                    )
                    FilterChip(modifier = Modifier.width(115.dp),
                        selected = selectedFilterChip06,
                        onClick = { selectedFilterChip06 = !selectedFilterChip06 },
                        label = {
                            Text(
                                "Comedia",
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        leadingIcon = {
                            if (selectedFilterChip06) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Checked",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        }
                    )
                }

                Text(
                    "(Debes seleccionar al menos uno)",
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    modifier = Modifier.width(160.dp),
                    shape = RoundedCornerShape(5.dp),
                    onClick = {
                        emailValue = ""
                        nameValue = ""
                        firstSurnameValue = ""
                        secondSurnameValue = ""
                        phoneValue = ""
                        selectedFilterChip01 = false
                        selectedFilterChip02 = false
                        selectedFilterChip03 = false
                        selectedFilterChip04 = false
                        selectedFilterChip05 = false
                        selectedFilterChip06 = false
                    }) {
                    Text("Borrar formulario")
                }

                Button(
                    modifier = Modifier.width(160.dp),
                    shape = RoundedCornerShape(5.dp),
                    onClick = {
                        if (selectedFilterChip01 ||
                            selectedFilterChip02 ||
                            selectedFilterChip03 ||
                            selectedFilterChip04 ||
                            selectedFilterChip05 ||
                            selectedFilterChip06
                        ) {
                            if (checks) {
                                errors = false
                                showDialog = true
                            } else {
                                errors = true
                                showDialog = false
                            }
                        } else {
                            errors = true
                            showDialog = false
                        }
                    }) {
                    Text("Registrarse")
                }
            }

            Spacer(Modifier.height(130.dp))

        }

        if (showDatePickerDialog) {
            Column(
                modifier = if (isSystemInDarkTheme()) Modifier
                    .width(350.dp)
                    .shadow(8.dp, RoundedCornerShape(16.dp))
                    .background(Color(0, 0, 0))
                    .align(Alignment.Center)
                    .clip(
                        RoundedCornerShape(15.dp),
                    ) else Modifier
                    .width(350.dp)
                    .shadow(8.dp, RoundedCornerShape(16.dp))
                    .background(Color(255, 255, 255, 255))
                    .align(Alignment.Center)
                    .clip(
                        RoundedCornerShape(15.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DatePicker(
                    state = dateOfBirth,
                    showModeToggle = true
                )
                Button(
                    onClick = { showDatePickerDialog = false },
                    modifier = Modifier.padding(vertical = 10.dp),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(text = "Aceptar")
                }
            }

        }

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .align(Alignment.BottomCenter)
                .zIndex(1f)
        ) {
            HorizontalDivider(
                thickness = 5.dp,
                color = Color(0xFF3EC745)
            )
            Spacer(Modifier.height(15.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profilepicture),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(5.dp, Color(0xFF3EC745), CircleShape)
                        .background(Color(0xFFDDDDDD))
                )
                Text("Unknown", fontSize = 40.sp, fontWeight = FontWeight.Bold)
            }
        }
    }

    if (showDialog) {
        RegistrationAlertDialog(onDismiss = { showDialog = false })
    }
}

@Composable
fun RegistrationAlertDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        icon = {
            Icon(
                imageVector = Icons.Default.ThumbUp,
                contentDescription = "Registro exitoso",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        title = {
            Text(
                text = "Registro en la APP",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Text(
                text = "El registro en la APP se ha realizado correctamente",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            TextButton(
                onClick = { onDismiss() }
            ) {
                Text("Aceptar")
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.Check, // Ícono del check
                    contentDescription = "Aceptar"
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surfaceVariant, // Color de fondo
        tonalElevation = 4.dp // Elevación del fondo
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    LoginUD04Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Greeting(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview2() {
    LoginUD04Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Greeting(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}