package com.pureKnowledge.salesApp.screen.customer

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier



@Composable
fun CustomerScreen(){
    var customersName by remember { mutableStateOf("") }
    Scaffold(
        content = {
            val padd = it
           CustomerUI(
                modifier = Modifier.padding(padd),
                addCustomer = { /*TODO*/ },
                onHomeClick = { /*TODO*/ },
                onStockRecordClick = { /*TODO*/ },
                onCustomerSearchClick = { /*TODO*/ },
                onPriceClick = { /*TODO*/ },
                onBackCLick = { /*TODO*/ },
                onSubmitClick = { /*TODO*/ },
                totalNumberOfCustomer = "200",
                schoolsNO = "200",
                repsNo = "200",
                publishersNo = "200",
                othersNo = "200",
                onCustomersNameChange = { customersName = it },
                onCustomersPhoneChange = { customersName = it },
                onCustomersAddressChange = { customersName = it },
                customersName = customersName,
                customersPhone = customersName,
                customersAddress = customersName,
                select = customersName,
                onSelectChange = { customersName = it }
            )

        }
    )
}