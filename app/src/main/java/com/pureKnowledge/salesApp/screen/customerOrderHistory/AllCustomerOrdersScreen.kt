package com.pureKnowledge.salesApp.screen.customerOrderHistory

import androidx.compose.runtime.Composable

@Composable
fun AllCustomerOrderScreen(){
    AllCustomersOrders(
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ },
        paymentBalance = "3,000",
        totalPayment = "5,000",
        clearanceBalance = "3,000"
    )
}