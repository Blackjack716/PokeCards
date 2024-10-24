package com.example.pokecards.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokecards.ui.theme.LocalPallet

@Composable
fun MainView(
    state: CharacterListState,
    onEvent: (CharacterEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .background(LocalPallet.current.backgroundColor)
                .padding(4.dp)
        )
    ) {
        CategoryBar(onEvent)
        CharacterList(state.characters, onEvent)
    }
}