package com.atom.myapp.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.atom.myapp.data.StoreDarkMode
import com.atom.myapp.data.StoreUserMail
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreUserMail(context)
    val darkModeStore = StoreDarkMode(context)

    val userMail = dataStore.getMail.collectAsState(initial = "")
    val darkMode = darkModeStore.getDarkMode.collectAsState(initial = false)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(text = "¡Pantalla de Inicio!", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Usuario conectado: ${userMail.value}")
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            scope.launch {
                darkModeStore.saveDarkMode(!darkMode.value)
            }
        }) {
            Text(if (darkMode.value) "Cambiar a Modo Claro" else "Cambiar a Modo Oscuro")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
            Text("Cerrar Sesión")
        }
    }
}