package com.pureKnowledge.salesApp.screen.order

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun OrderDetailsScreen(){
    val hideTopAndBottomBar by remember { mutableStateOf(true) }

    OrderDetailsUI(
        modifier = Modifier.fillMaxSize(),
        onEditClick = { /*TODO*/ },
        onRemoveClick = { /*TODO*/ },
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ },
        grossTotal = "₦50,000",
        lessDiscount = "₦5,000",
        netTotal = "₦45,000",
        paid = "₦20,000",
        balance = "₦25,000",
        hideTopAndBottomBar = hideTopAndBottomBar

    )
}