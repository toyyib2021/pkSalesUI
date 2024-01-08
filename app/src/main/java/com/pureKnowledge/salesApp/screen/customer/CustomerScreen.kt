package com.pureKnowledge.salesApp.screen.customer

import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pureKnowledge.salesApp.screen.viewModel.CustomerTypeViewModel
import com.pureKnowledge.salesApp.screen.viewModel.CustomerViewModel


@Composable
fun CustomerScreen(
    addCustomer:()->Unit,
    onBackCLick:()->Unit,
    onHistroyClick:(String)->Unit,
    onPlaceOrderClick:(String)->Unit,
){
    val customerViewModel: CustomerViewModel = viewModel()
    var search by remember { mutableStateOf("") }
    var editCustomerState by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf("") }

    val customerTypeViewModel: CustomerTypeViewModel = viewModel()
    val listOfOption = customerTypeViewModel.data.value.map { it.customerType }
    val customerTypeInList = customerViewModel.data.value.distinctBy { it.customerType }
    val customerTypeCount = customerViewModel.data.value.map { it.customerType }
    val customerTypes = customerTypeInList.map { it.customerType }
//    customerTypes.forEach {
//        Log.d(TAG, "CustomerScreen: $it")
//    }


    BackHandler() {onBackCLick()}
    Scaffold {
        val padd = it
        CustomerUI(
            modifier = Modifier.padding(padd),
            addCustomer = addCustomer,
            onHomeClick = { /*TODO*/ },
            onStockRecordClick = { /*TODO*/ },
            onCustomerSearchClick = { /*TODO*/ },
            onPriceClick = { /*TODO*/ },
            onBackCLick = onBackCLick,
            onSubmitClick = {
                customerViewModel.customerObject.date.value = "2024-01-08"
                val anyEmpty = customerViewModel.customerObject.run {
                    listOf(customerName.value, phone.value, address.value, customerType.value, date.value)
                        .any { it == "" }
                }
                if (anyEmpty){
                    errorMsg = "Some Fields are Empty"
                }else{
                    customerViewModel.updateCustomer()
                    editCustomerState = false
                    errorMsg = ""
                }
            },
            totalNumberOfCustomer = customerViewModel.data.value.size.toString(),
            onCustomersNameChange = { customerViewModel.customerObject.customerName.value = it },
            onCustomersPhoneChange = { customerViewModel.customerObject.phone.value = it },
            onCustomersAddressChange = { customerViewModel.customerObject.address.value = it },
            customersName = customerViewModel.customerObject.customerName.value,
            customersPhone = customerViewModel.customerObject.phone.value,
            customersAddress = customerViewModel.customerObject.address.value,
            select = customerViewModel.customerObject.customerType.value,
            onSelectChange = { customerViewModel.customerObject.customerType.value = it },
            onSearchChange = { search = it },
            onSearchClick = {
                customerViewModel.customerObject.customerName.value = search
                customerViewModel.filterCustomerByName()
            },
            search = search,
            customerList = customerViewModel.filterData.value.sortedByDescending { it._id },
            onUpdateCustomerClick = { customer ->
                editCustomerState = true
                customerViewModel.objectId.value = customer._id.toHexString()
                customerViewModel.customerObject.customerName.value = customer.customerName
                customerViewModel.customerObject.phone.value = customer.phone
                customerViewModel.customerObject.address.value = customer.address
                customerViewModel.customerObject.customerType.value = customer.customerType
            },
            onHistroyClick = {onHistroyClick(it._id.toHexString())},
            onPlaceOrderClick = {onPlaceOrderClick(it._id.toHexString())},
            onBackUpdateCLick = { editCustomerState = false },
            editCustomerState = editCustomerState,
            onDeleteClick = { openDialog = true },
            onDismissRequest = { openDialog = false },
            onNoClick = { openDialog = false },
            onYesClick = {
                customerViewModel.deleteCustomer()
                openDialog = false
                editCustomerState = false
            },
            openDialog = openDialog,
            title = "Customer",
            delete = customerViewModel.customerObject.customerName.value,
            customerType = customerTypes,
            customerTypeCount = customerTypeCount,
            onCustomerTypeClick = { type ->
                customerViewModel.customerObject.customerType.value = type
                customerViewModel.filterCustomerByCustomerType()
            },
            onAddTypeClick = {},
            listOfOption = listOfOption,
            errorMsg = errorMsg
        )
    }
}