package com.pureKnowledge.salesApp.screen.revenue

import androidx.compose.runtime.Composable

@Composable
fun RevenueScreen(){
    RevenueUI(
        ondownloadClick = { /*TODO*/ },
        ondownloadMonthlyClick = { /*TODO*/ },
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ },
        onEditClick = { /*TODO*/ },
        onRemoveClick = { /*TODO*/ },
        grossTotal = "₦50,000",
        lessDiscount = "₦5,000",
        netTotal = "₦45,000",
        paid = "₦20,000",
        balance = "₦25,000"
    )
}