package com.mmfsin.ved.presentation.core.components

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun StatusBarColor(color: Color = Color.White, darkIcons: Boolean = true) {
    val view = LocalView.current
    SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor = color.toArgb()
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = darkIcons
    }
}

@Preview
@Composable
fun LoadingFullScreen() {
    Box(Modifier.fillMaxSize().background(Color.White), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            Modifier.size(64.dp),
            strokeWidth = 12.dp,
            color = Color.Blue,
            strokeCap = StrokeCap.Round
        )
    }
}