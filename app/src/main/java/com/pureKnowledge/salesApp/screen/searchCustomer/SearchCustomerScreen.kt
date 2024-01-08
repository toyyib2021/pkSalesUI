package com.pureKnowledge.salesApp.screen.searchCustomer

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pureKnowledge.salesApp.screen.viewModel.CustomerViewModel

@Composable
fun SearchCustomerScreen(
    onBackCLick:()->Unit,
    addCustomer:()->Unit,
    onHistroyClick:(String)->Unit,
    onPlaceOrderClick:(String)->Unit,
){

    val customerViewModel: CustomerViewModel = viewModel()

    BackHandler{onBackCLick()}
    var value by remember { mutableStateOf("") }
    SearchCustomerUI(
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },

        addCustomer = addCustomer,
        onBackCLick = onBackCLick,
        value = customerViewModel.customerObject.customerName.value,
        onValueChange = { customerViewModel.customerObject.customerName.value = it },
        onSearchClick = {  customerViewModel.filterCustomerByName() },
        customerList = customerViewModel.filterData.value.sortedByDescending { it._id },
        onPlaceOrderClick = {onPlaceOrderClick(it._id.toHexString())},
        onHistroyClick = { onHistroyClick(it._id.toHexString()) },
        onUpdateCustomerClick = {}

    )

}