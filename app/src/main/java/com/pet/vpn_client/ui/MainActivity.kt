package com.pet.vpn_client.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.pet.vpn_client.presentation.view_model.VpnScreenViewModel
import com.pet.vpn_client.ui.navigation.BottomNavigationBar
import com.pet.vpn_client.ui.navigation.Navigation
import com.pet.vpn_client.ui.theme.VPN_ClientTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VPN_ClientTheme {
                NavigationScreen()
            }
        }
    }
}

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Navigation(navController = navController, innerPadding = innerPadding)
    }
}