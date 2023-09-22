package com.ilhomsoliev.consolepro.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ilhomsoliev.consolepro.app.navigation.Navigation
import com.ilhomsoliev.consolepro.ui.theme.ConsoleProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConsoleProTheme {
                val navController: NavHostController = rememberNavController()
                Surface(Modifier.fillMaxSize()) {
                    Navigation(navController)
                }
            }
        }
    }
}