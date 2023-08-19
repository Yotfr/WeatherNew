package ru.yotfr.places.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.yotfr.places.viewmodel.FavoritePlacesViewModel
import ru.yotfr.shared.model.PlaceModel

@Composable
fun FavoritePlacesScreen(
    vm: FavoritePlacesViewModel = hiltViewModel(),
    navigateToSearchPlaces: () -> Unit
) {
    LaunchedEffect(Unit) {
        vm.openedScreen()
    }

    val state by vm.screenState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(onClick = navigateToSearchPlaces) {
            Text(text = "Search places")
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn {
            items(state.places) { placeModel ->
                FavoritePlaceItem(placeModel = placeModel)
            }
        }
    }
}

@Composable
fun FavoritePlaceItem(placeModel: PlaceModel) {
    Row {
        Text(
            text = placeModel.name
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (placeModel.isLocation) {
            Icon(
                imageVector = Icons.Outlined.Place,
                contentDescription = null
            )
        }
    }
}