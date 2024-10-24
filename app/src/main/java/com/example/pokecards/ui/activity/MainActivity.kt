package com.example.pokecards.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.pokecards.ui.compose.MainView
import com.example.pokecards.ui.theme.AppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {

                var state by remember {
                    mutableStateOf(mainViewModel.state.value)
                }

                LaunchedEffect(mainViewModel.state) {
                    mainViewModel.state.collect {
                        state = it
                    }

                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainView(
                        state = state,
                        onEvent = mainViewModel::onEvent,
                        modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}