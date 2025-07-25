package com.pet.vpn_client.presentation.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pet.vpn_client.presentation.intent.QrCodeScreenIntent
import com.pet.vpn_client.presentation.view_model.QrCodeScreenViewModel
import com.pet.vpn_client.presentation.composable_elements.CameraView
import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import kotlinx.coroutines.delay

@Composable
fun QrCodeScreen(
    modifier: Modifier = Modifier,
    viewModel: QrCodeScreenViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onResult: () -> Unit
) {
    var hasCameraPermission by remember { mutableStateOf(false) }
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { permission ->
        hasCameraPermission = permission
    }

    LaunchedEffect(Unit) {
        val cameraPermissionGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
        if (cameraPermissionGranted) {
            hasCameraPermission = true
        } else {
            launcher.launch(Manifest.permission.CAMERA)
        }
    }
    if (hasCameraPermission) {
        Box(modifier = modifier.fillMaxSize()) {
            CameraView(
                modifier = Modifier.fillMaxSize(),
                onFrame = viewModel::onAnalyzeFrame
            )
            if (state.error != null) {
                Text(
                    text = "Ошибка: ${state.error}",
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(16.dp),
                    color = Color.Red
                )
            }
        }
    } else {
        //TODO уведомление о необходимости разрешения
    }

    LaunchedEffect(state.configFound) {
        //TODO возможно необходимо обработать UX считывания конфигурации или возможных ошибок
        if (state.configFound) {
            delay(300)
            viewModel.onIntent(QrCodeScreenIntent.ResetState)
            onResult()
        }
    }
}