package com.pureKnowledge.salesApp.screen.stock

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*


@Composable
fun StockHistoryScreen(){

    val items = listOf(
        "Item 1",
        "Item 1", "Item 2", "Item 3", "Item 4", "Item 5",)

    StockHistoryUI(
        ondownloadClick = { /*TODO*/ },
        onQtyClick = { /*TODO*/ },
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = {/*TODO*/ },
        items = items
    )

}