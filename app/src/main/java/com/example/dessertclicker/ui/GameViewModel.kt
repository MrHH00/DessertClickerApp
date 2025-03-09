package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.data.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    fun onDessertLeftClicked() {
        _uiState.update { cupcakeUiState ->
            val dessertsSold = (cupcakeUiState.dessertsSold + 1).coerceAtLeast(0)
            val prevDessertIndex = determineDessertIndex(dessertsSold)
            cupcakeUiState.copy(
                currentDessertIndex = prevDessertIndex,
                revenue = (cupcakeUiState.revenue + cupcakeUiState.DessertLeftPrice).coerceAtLeast(0),
                dessertsSold = dessertsSold,
                leftCurrentDessertImageId = dessertList[prevDessertIndex].imageId,
                DessertLeftPrice = dessertList[prevDessertIndex].price
            )
        }
    }

    fun onDessertRightClicked() {
        _uiState.update { cupcakeUiState ->
            val dessertsSold = cupcakeUiState.dessertsSold + 1
            val nextDessertIndex = determineDessertIndex(dessertsSold)
            cupcakeUiState.copy(
                currentDessertIndex = nextDessertIndex,
                revenue = cupcakeUiState.revenue + cupcakeUiState.DessertRightPrice,
                dessertsSold = dessertsSold,
                rightCurrentDessertImageId = dessertList[nextDessertIndex].imageId,
                DessertRightPrice = dessertList[nextDessertIndex].price
            )
        }
    }

    private fun determineDessertIndex(dessertsSold: Int): Int {
        var dessertIndex = 0
        for (index in dessertList.indices) {
            if (dessertsSold >= dessertList[index].startProductionAmount) {
                dessertIndex = index
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more
                // desserts, you'll start producing more expensive desserts as determined by
                // startProductionAmount. We know to break as soon as we see a dessert who's
                // "startProductionAmount" is greater than the amount sold.
                break
            }
        }
        return dessertIndex
    }
}