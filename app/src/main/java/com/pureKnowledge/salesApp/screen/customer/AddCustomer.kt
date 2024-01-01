package com.pureKnowledge.salesApp.screen.customer

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.pureKnowledge.salesApp.screen.component.UpdateOrAddCustomerInfo
import com.pureKnowledge.salesApp.util.Constants.customerStatus
import com.pureknowledge.salesApp.R

@Composable
fun AddCustomerScreen(){
    var customersName by remember { mutableStateOf("select") }

        AddCustomer(
            onBackClick = { /*TODO*/ },
            onHomeClick = { /*TODO*/ },
            onStockRecordClick = { /*TODO*/ },
            onCustomerSearchClick = { /*TODO*/ },
            onPriceClick = { /*TODO*/ },
            onSubmitClick = { /*TODO*/ },
            customersName = customersName,
            customersPhone = customersName,
            customersAddress = customersName,
            select = customersName,
            onCustomersNameChange = { customersName = it },
            onCustomersAddressChange = { customersName = it },
            onCustomersPhoneChange = { customersName = it },
            onSelectChange = { customersName = it },
        )

}





@Composable
fun AddCustomer(
    onBackClick:()->Unit,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    onSubmitClick:()->Unit,
    customersName: String,
    customersPhone: String,
    customersAddress: String,
    select: String,
    onCustomersNameChange: (String)->Unit,
    onCustomersAddressChange:(String)->Unit,
    onCustomersPhoneChange:(String)->Unit,
    onSelectChange:(String)->Unit,

){

    UpdateOrAddCustomerInfo(
        onBackCLick =onBackClick ,
        onHomeClick = onHomeClick,
        onStockRecordClick = onStockRecordClick,
        onCustomerSearchClick = onCustomerSearchClick,
        onPriceClick = onPriceClick ,
        icon = painterResource(id = R.drawable.add),
        title ="Customer",
        onSubmitClick = onSubmitClick,
        onCustomersNameChange = {onCustomersNameChange(it)},
        onCustomersPhoneChange = {onCustomersPhoneChange(it)},
        onCustomersAddressChange = {onCustomersAddressChange(it)},
        customersName = customersName,
        customersPhone = customersPhone,
        customersAddress = customersAddress,
        select = select,
        listOfOption = customerStatus,
        onSelectChange = {onSelectChange(it)}
    )
}