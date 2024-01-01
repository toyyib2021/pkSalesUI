package com.pureKnowledge.salesApp.screen.searchCustomer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun SearchCustomerScreen(){

    var value by remember { mutableStateOf("") }
    SearchCustomerUI(
        addCustomer = { /*TODO*/ },
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ },
        value = value,
        onValueChange = { value = it },
        onSearchClick = { /*TODO*/ }
    )

}