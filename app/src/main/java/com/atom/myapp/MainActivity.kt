package com.atom.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.atom.myapp.data.StoreDarkMode
import com.atom.myapp.navigation.NavManager
import com.atom.myapp.ui.theme.MyLoginTheme // Si tu carpeta de temas se llama distinto, ajústalo aquí

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val darkModeStore = StoreDarkMode(this)
            val darkMode by darkModeStore.getDarkMode.collectAsState(initial = false)

            MyLoginTheme(darkTheme = darkMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager()
                }
            }
        }
    }
}