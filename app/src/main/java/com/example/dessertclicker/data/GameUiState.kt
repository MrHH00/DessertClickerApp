package com.example.dessertclicker.data

import androidx.annotation.DrawableRes
import com.example.dessertclicker.data.Datasource.dessertList

data class GameUiState(
    val currentDessertIndex: Int = 0,
    val dessertsSold: Int = 0,
    val revenue: Int = 0,
    //left dessert
    val DessertLeftPrice: Int = dessertList[currentDessertIndex].price,
    @DrawableRes val leftCurrentDessertImageId: Int = dessertList[currentDessertIndex].imageId,
    //right dessert
    val DessertRightPrice: Int = dessertList[currentDessertIndex].price,
    @DrawableRes val rightCurrentDessertImageId: Int = dessertList[currentDessertIndex].imageId,

)