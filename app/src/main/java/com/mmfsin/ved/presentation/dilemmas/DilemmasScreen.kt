package com.mmfsin.ved.presentation.dilemmas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun DilemmasScreenPV() {
    DilemmasScreen()
}

@Composable
fun DilemmasScreen() {
    Scaffold { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().background(Color.Blue)) {

        }
    }
}