package com.pureKnowledge.salesApp.screen.revenue

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun RevenueConfirmationScreen(){
    var openDialog by remember { mutableStateOf(false) }
    var amount by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    RevenueConfirmationUI(
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
        balance = "₦25,000",
        onSubTextCLick = { openDialog = true },
        amount = amount,
        pin = pin,
        openDialog = openDialog,
        onBtnClick = { openDialog = false },
        onPinChange ={ pin = it },
        onAmountChange = { amount = it},
        onDismissRequest = { openDialog = false },
    )
}